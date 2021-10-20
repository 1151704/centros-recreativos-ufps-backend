package ufps.centrosrecreativos.api.auth.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ufps.centrosrecreativos.api.auth.ConstantsAuth;
import ufps.centrosrecreativos.api.auth.EpsAuthenticationToken;
import ufps.centrosrecreativos.api.auth.service.JWTService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(ConstantsAuth.HEADER_AUTHORIZACION_KEY);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		boolean validToken = jwtService.validate(header);

		EpsAuthenticationToken authentication = null;

		if (validToken) {

			String username = jwtService.getUserName(header);

			List<GrantedAuthority> roles = new ArrayList<>();
			try {
				roles = (List<GrantedAuthority>) jwtService.getRoles(header);
			} catch (IOException e) {
			} catch (Exception e) {
			}

			authentication = new EpsAuthenticationToken(username, null, jwtService.getClaims(header), roles);

		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);

	}

	protected boolean requiresAuthentication(String header) {

		return !(header == null || !header.startsWith(ConstantsAuth.TOKEN_BEARER_PREFIX));

	}

}
