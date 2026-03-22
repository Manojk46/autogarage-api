package com.garage.autogarage.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.garage.autogarage.service.LoginUserDetailsService;


@Configuration
@EnableWebSecurity
public class LoginConfiguration {
	@Autowired
	LoginUserDetailsService service;
	

	@Bean
	public SecurityFilterChain srf(HttpSecurity httpSecurity) {
		httpSecurity
					.csrf(c->c.disable())
					.authorizeHttpRequests(s->s.requestMatchers("/home","/register","/login")
							.permitAll()
							.anyRequest()
							.authenticated())
					.httpBasic(org.springframework.security.config.Customizer.withDefaults())
					.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();		    
					
	}
	@Bean
	public AuthenticationProvider authprovider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(service);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
}
