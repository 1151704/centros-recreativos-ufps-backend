package ufps.centrosrecreativos.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ufps.centrosrecreativos.api.model.CruUsuario;
import ufps.centrosrecreativos.api.service.CruUsuarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class EpsAuthenticationManager implements AuthenticationManager {

	@Autowired
	private CruUsuarioService usuarioService;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		@SuppressWarnings("unchecked")
		HashMap<String, Object> details = (HashMap<String, Object>) authentication.getDetails();

		CruUsuario respuesta = usuarioService.findByUsername(username);

		if (respuesta != null) {
			if (respuesta.getRol() == null) {
				throw new BadCredentialsException("No cuenta con ningun rol");
			}

			if (!respuesta.getEnable()) {
				throw new BadCredentialsException("Usuario inhabilitado");
			}
		} else {
			throw new BadCredentialsException("Usuario no registrado");
		}
		boolean porRestablecer = false;
		if (respuesta.getFechaRestablecer() != null) {
			long diff = new Date().getTime() - respuesta.getFechaRestablecer().getTime();
			long diferenciaHoras =  diff / (60 * 60 * 1000);

			if (diferenciaHoras <= 24) {
				if (passwordEncoder.matches(password, respuesta.getPasswordTemporal()) || passwordEncoder.matches(password, respuesta.getPassword())) {
					porRestablecer = true;
				} else {
					throw new BadCredentialsException("Contraseña invalida");
				}
			}
		}
		if (!passwordEncoder.matches(password, respuesta.getPassword()) && !porRestablecer) {
			throw new BadCredentialsException("Contraseña o nombre de usuario invalidos");
		}

		details = new HashMap<>();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		details.put("token", respuesta);

		authorities.add(new SimpleGrantedAuthority(respuesta.getRol().getRol()));
		details.put("role", respuesta.getRol().getRol());

		return new EpsAuthenticationToken(username, password, details, authorities);		
	}

}
