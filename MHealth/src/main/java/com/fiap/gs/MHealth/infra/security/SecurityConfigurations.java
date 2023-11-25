package com.fiap.gs.MHealth.infra.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fiap.gs.MHealth.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.
	
		authorizeHttpRequests()
		
		.anyRequest()
		.authenticated().and().formLogin()
			.loginPage("/login").defaultSuccessUrl("/pedidos", true)
			.permitAll().and().logout().permitAll();
		
		
		http.csrf().disable()
			.userDetailsService(userService);
		return http.build();
	} 

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();    
	}
	
}
