package com.palaceflophouse.supportportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Author: Brandon Shaffer
 * Date: 7/17/2022
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/user-homepage").access("hasRole('USER')")
				.antMatchers("/", "/**").access("permitAll")

				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/user-homepage", true)

				.and()
				.logout()
				.logoutSuccessUrl("/")

				// Make H2-Console non-secured; for debug purposes
//				.and()
//				.csrf()
//				.ignoringAntMatchers("/h2-console/**")
//
//				// Allow pages to be loaded in frames from the same origin; needed for H2-Console
//				.and()
//				.headers()
//				.frameOptions()
//				.sameOrigin()
		;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {

		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(encoder());

	}
}
