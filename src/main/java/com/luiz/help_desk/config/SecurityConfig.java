package com.luiz.help_desk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.luiz.help_desk.security.JWTAuthenticationFilter;
import com.luiz.help_desk.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private static final String[] PUBLIC_MATCHERS = {"/tecnicos/**"};
	
	@Autowired
	private JWTUtil jwtUtil;
	
	// Filtros (filters)
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
	//	http.addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtil));
		
		return http
				.authorizeHttpRequests(
						AuthorizeConfig -> {
							AuthorizeConfig.requestMatchers(PUBLIC_MATCHERS).permitAll();
							AuthorizeConfig.requestMatchers("/clientes/**").permitAll();
							AuthorizeConfig.requestMatchers("/login").permitAll();
							AuthorizeConfig.anyRequest().authenticated();
						})
				.formLogin(Customizer.withDefaults())
				.build();
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}