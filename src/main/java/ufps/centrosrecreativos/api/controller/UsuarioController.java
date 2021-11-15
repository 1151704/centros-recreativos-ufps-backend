package ufps.centrosrecreativos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufps.centrosrecreativos.api.container.UsuarioApi;
import ufps.centrosrecreativos.api.container.UsuarioEntradaApi;
import ufps.centrosrecreativos.api.container.UsuariosApi;
import ufps.centrosrecreativos.api.model.CruRol;
import ufps.centrosrecreativos.api.model.CruUsuario;
import ufps.centrosrecreativos.api.service.CruRolService;
import ufps.centrosrecreativos.api.service.CruTipoIdentificacionService;
import ufps.centrosrecreativos.api.service.CruUsuarioService;
import ufps.centrosrecreativos.api.util.ValidationException;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/usuario/")
public class UsuarioController {

    @Autowired
    private CruUsuarioService service;

    @Autowired
    private CruRolService rolService;

    @Autowired
    private CruTipoIdentificacionService tipoIdService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @SuppressWarnings("unchecked")
    @Secured({ "ROLE_ADMIN" })
    @GetMapping("all")
    public UsuariosApi getListUsuarios() {
        UsuariosApi api = new UsuariosApi();

        api.setUsuarios(service.findAll());

        return api;
    }

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("byRol/{rol}")
    public UsuariosApi getListUsuariosByRol(@PathVariable String rol) {
        UsuariosApi api = new UsuariosApi();
        CruRol rolSearch = rolService.findByRol(rol);

        if (rolSearch != null) {
            api.setUsuarios(service.findByRol(rolSearch));
        }
        return api;
    }

    @SuppressWarnings("unchecked")
    @Secured({ "ROLE_ADMIN" })
    @GetMapping("byUsuario/{identificacion}")
    public UsuarioApi getUsuario(@PathVariable String identificacion) {
        UsuarioApi api = new UsuarioApi();
        api.setUsuario(service.findByIdentificacion(identificacion));
        return api;
    }

    @PostMapping("passwordEdit")
    public ResponseEntity<Boolean> editPassword(@RequestBody String passwordNew) {
        Boolean hecho = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CruUsuario usuarioActual = service.findByUsername(authentication.getName());

        if (usuarioActual != null) {

            if (passwordNew != null && passwordNew.trim().length() > 4) {

                usuarioActual.setPassword(passwordEncoder.encode(passwordNew));
                try {
                    service.save(usuarioActual);
                    hecho = true;
                } catch (Exception e) {
                    throw new ValidationException("Error de Operación", HttpStatus.BAD_REQUEST, e);
                }

            } else {
                throw new ValidationException("La contraseña no cumple con los criterios de seguiridad",
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ValidationException("Usuario inexistente", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(hecho, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @Secured({ "ROLE_ADMIN" })
    @PostMapping("enableEdit/{identificacion}/{enableNew}")
    public ResponseEntity<Boolean> editEstado(@PathVariable String identificacion, @PathVariable Boolean enableNew) {

        CruUsuario usuario = service.findByIdentificacion(identificacion);

        if (usuario != null) {

            if (enableNew != null && enableNew != usuario.getEnable()) {

                usuario.setEnable(enableNew);
                try {
                    service.save(usuario);
                } catch (Exception e) {
                    throw new ValidationException("Error de  operación", HttpStatus.BAD_REQUEST, e);
                }

            } else {
                throw new ValidationException("El estado no ha cambiado", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ValidationException("Usuario inexistente", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @Secured({ "ROLE_ADMIN" })
    @PostMapping("")
    public ResponseEntity<UsuarioApi> save(@RequestBody(required = false) UsuarioEntradaApi entrada) {

        if (entrada != null && entrada.getIdentificacion() != null && !entrada.getIdentificacion().isEmpty()
                && entrada.getIdentificacion().length() > 5) {

            entrada.setIdentificacion(entrada.getIdentificacion().trim());

            CruUsuario usuario = service.findByIdentificacion(entrada.getIdentificacion());

            if (usuario == null) {
                UsuarioApi api = new UsuarioApi();
                CruUsuario usuarioSave;

                usuario = new CruUsuario();
                usuario.setIdentificacion(entrada.getIdentificacion());
                usuario.setNombres(entrada.getNombres());
                usuario.setApellidos(entrada.getApellidos());

                usuario.setEmail(entrada.getEmail());
                usuario.setEnable(entrada.getEnable());
                usuario.setFechaActualizacion(new Date());
                usuario.setRol(rolService.findByRol(entrada.getRol()));

                try {
                    usuario.setTipoId(tipoIdService.findByTipo(entrada.getTipoId()));
                } catch (Exception e) {
                    throw new ValidationException(e.getMessage(), HttpStatus.BAD_REQUEST);
                }

                if (!verificarEmail(entrada.getEmail())) {
                    throw new ValidationException("El correo electronico ingresado ya existe", HttpStatus.BAD_REQUEST);
                }

                usuario.setPassword(passwordEncoder.encode(usuario.getIdentificacion()));
                usuario.setUsername(crearNombreUsuario(usuario));

                try {
                    usuarioSave = service.save(usuario);
                    api.setUsuario(usuarioSave);
                } catch (Exception e) {
                    throw new ValidationException(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(api, HttpStatus.OK);
            } else {
                throw new ValidationException(
                        "El usuario con identificación '" + entrada.getIdentificacion() + "' ya existe",
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ValidationException("La identificación '" + entrada.getIdentificacion() + "' es invalida",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("update")
    public ResponseEntity<UsuarioApi> update(@RequestBody(required = false) UsuarioEntradaApi entrada) {

        if (entrada != null && entrada.getIdentificacion() != null && !entrada.getIdentificacion().isEmpty()
                && entrada.getIdentificacion().length() > 5) {

            CruUsuario usuario = service.findByIdentificacion(entrada.getIdentificacion());

            if (usuario != null) {

                UsuarioApi api = new UsuarioApi();

                CruRol rol = usuario.getRol();
                try {
                    if (rol == null || !rol.getRol().equals(entrada.getRol())) {
                        rol = rolService.findByRol(entrada.getRol());
                    }
                } catch (Exception e) {
                }

                CruUsuario usuarioEdit = new CruUsuario();

                // VALORES NO CAMBIANTES
                usuarioEdit.setId(usuario.getId());
                usuarioEdit.setIdentificacion(usuario.getIdentificacion());
                usuarioEdit.setTipoId(usuario.getTipoId());
                usuarioEdit.setNombres(usuario.getNombres());
                usuarioEdit.setApellidos(usuario.getApellidos());
                usuarioEdit.setPassword(usuario.getPassword());
                usuarioEdit.setUsername(usuario.getUsername());
                usuarioEdit.setFechaRegistro(usuario.getFechaRegistro());

                if (!usuario.getEmail().equalsIgnoreCase(entrada.getEmail())) {
                    if (!verificarEmail(entrada.getEmail())) {
                        throw new ValidationException("El correo electronico ingresado ya existe",
                                HttpStatus.BAD_REQUEST);
                    }
                }

                // VALORES CAMBIANTES
                usuarioEdit.setEmail(entrada.getEmail());
                usuarioEdit.setEnable(entrada.getEnable());
                usuarioEdit.setFechaActualizacion(new Date());
                usuarioEdit.setRol(rol);

                try {
                    usuarioEdit = service.save(usuarioEdit);
                    api.setUsuario(usuarioEdit);
                } catch (Exception e) {
                    throw new ValidationException("Error de operación", HttpStatus.BAD_REQUEST, e);
                }
                return new ResponseEntity<>(api, HttpStatus.OK);
            } else {
                throw new ValidationException(
                        "El usuario con identificación '" + entrada.getIdentificacion() + "' no existe",
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ValidationException("La identificación '" + entrada.getIdentificacion() + "' es invalida",
                    HttpStatus.BAD_REQUEST);
        }

    }

    private boolean verificarEmail(String email) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        CruUsuario usuario = service.findByEmail(email);
        if (usuario != null) {
            return false;
        }

        return true;
    }

    private String crearNombreUsuario(CruUsuario usuario) {
        return "ADMI" + usuario.getIdentificacion();
    }
}
