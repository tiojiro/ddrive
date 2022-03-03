package com.dswiss.api.ddrive.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}user").roles("USER");
		auth.eraseCredentials(false);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/ddrive/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/ddrive/**").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/ddrive/**").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/ddrive/**").hasRole("USER")
		.and()
		.csrf()
		.disable()
		.formLogin()
		.disable();
	}

}