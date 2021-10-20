package ufps.centrosrecreativos.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class ValidateController {

	@GetMapping("/validate")
	public boolean validate(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			return true;
		}

		return false;
	}

}
