package com.example.demo.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/compte").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/compte/*").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/client/*").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/client").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/client").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/client/*").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/client/*").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		
	}

}
