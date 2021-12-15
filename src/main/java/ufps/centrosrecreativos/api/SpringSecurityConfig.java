package ufps.centrosrecreativos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfigurationSource;
import ufps.centrosrecreativos.api.auth.EpsAuthenticationManager;
import ufps.centrosrecreativos.api.auth.filter.JWTAuthenticationFilter;
import ufps.centrosrecreativos.api.auth.filter.JWTAuthorizationFilter;
import ufps.centrosrecreativos.api.auth.service.JWTService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private CorsConfigurationSource corsConfigurationSource;

	@Autowired
	private EpsAuthenticationManager epsAuthenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
				.configurationSource(corsConfigurationSource).and().csrf().disable().authorizeRequests().and()
				.authorizeRequests()
				.antMatchers("/", "/validate", "/files/**", "/images/**", "/favicon.ico",
						"/api/centro-recreativo/enabled", "/api/comuna/all", "/api/calificacion/**")
				.permitAll().antMatchers(HttpMethod.POST, "/api/usuario/restablecer", "/api/calificacion/save").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
				.addFilter(new JWTAuthenticationFilter(epsAuthenticationManager, jwtService))
				.addFilter(new JWTAuthorizationFilter(epsAuthenticationManager, jwtService));

		http.headers().frameOptions().disable();
	}

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);
		return firewall;
	}

}
