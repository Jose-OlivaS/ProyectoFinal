package com.example.proyectofinalbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
 	public UserDetailsService userDetailsService() {
		
		PasswordEncoder encoder = passwordEncoder();
		
 		UserDetails user = User.builder().passwordEncoder(encoder::encode)
 			.username("user")
 			.password("1234")
 			.roles("USER")
 			.build();
 		UserDetails admin = User.builder().passwordEncoder(encoder::encode)
 			.username("admin")
 			.password("1234")
 			.roles("ADMIN", "USER")
 			.build();
 		return new InMemoryUserDetailsManager(user, admin);
 	}
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 		.authorizeRequests()
 		.antMatchers("/css/**","/js/**","/images/**","/h2-console/**").permitAll()
 		.anyRequest().hasRole("USER")
 		.and()
 		.formLogin().loginPage("/login").permitAll() 
 		.and()
 		.logout().permitAll(); 
 		

 		http.csrf().disable();
 		http.headers().frameOptions().disable();

 		return http.build();
 	}

		
		
}
