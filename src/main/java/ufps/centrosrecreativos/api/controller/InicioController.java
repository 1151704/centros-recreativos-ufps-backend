package ufps.centrosrecreativos.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InicioController {

	@GetMapping("/")
	public String inicio(HttpServletRequest request) {
		return "API REST CENTROS RECREATIVOS FROM IP(" + request.getRemoteAddr() + ")";
	}

}
