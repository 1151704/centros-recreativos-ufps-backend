package ufps.centrosrecreativos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ufps.centrosrecreativos.api.container.*;
import ufps.centrosrecreativos.api.model.CruCentroRecreativo;
import ufps.centrosrecreativos.api.model.CruUsuario;
import ufps.centrosrecreativos.api.service.CruCentroRecreativoService;
import ufps.centrosrecreativos.api.service.CruComunaService;
import ufps.centrosrecreativos.api.util.ValidationException;

import java.util.Date;

@RestController
@RequestMapping("/api/centro-recreativo/")
public class CentroRecreativoController {

    @Autowired
    private CruCentroRecreativoService service;

    @Autowired
    private CruComunaService comunaService;

    @GetMapping("all")
    public CentrosRecreativosApi getAllCentrosRecreativos() {
        CentrosRecreativosApi centros = new CentrosRecreativosApi();

        try {
            centros.setCentros(service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return centros;
    }

    @GetMapping("enabled")
    public CentrosRecreativosApi getEnabledCentrosRecreativos() {
        CentrosRecreativosApi centros = new CentrosRecreativosApi();

        try {
            centros.setCentros(service.findEnable());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return centros;
    }

    @GetMapping("{id}")
    public CentroRecreativoApi getAllCentrosRecreativos(@PathVariable Integer id) {
        CentroRecreativoApi centros = new CentroRecreativoApi();

        try {
            centros.setCentro(service.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return centros;
    }

    @PostMapping("save")
    public ResponseEntity<CentroRecreativoApi> save(@RequestBody(required = false) CentroEntradaApi entrada) {

        CentroRecreativoApi api = new CentroRecreativoApi();
        CruCentroRecreativo centro;
        if (entrada.getId() != null) {
            centro = service.findById(entrada.getId());
        } else {
            centro = new CruCentroRecreativo();
        }
        centro.setDescripcionCentro(entrada.getDescripcionCentro());
        centro.setPosicionX(entrada.getPosicionX());
        centro.setPosicionY(entrada.getPosicionY());
        centro.setDireccionCentro(entrada.getDireccionCentro());
        centro.setNombreCentro(entrada.getNombreCentro());
        centro.setEnable(entrada.getEnable());
        centro.setComunaId(comunaService.findById(entrada.getComunaId()));

        try {
            api.setCentro(service.save(centro));
        } catch (Exception e) {
            throw new ValidationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @Secured({ "ROLE_ADMIN" })
    @PostMapping("enableEdit/{id}/{enableNew}")
    public ResponseEntity<Boolean> editEstado(@PathVariable Integer id, @PathVariable Boolean enableNew) {

        CruCentroRecreativo usuario = service.findById(id);

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
            throw new ValidationException("Centro inexistente", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
