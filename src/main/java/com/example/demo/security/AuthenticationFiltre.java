package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFiltre extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword(),new ArrayList<>());
			// TODO Auto-generated method stub
			return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//  generated Toke
		String username = ((User)authResult.getPrincipal()).getUsername();
		Algorithm algorithm = Algorithm.HMAC256(ConstantsSecurity.TOKEN_SECRET);
		String Jwt = JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis()+ConstantsSecurity.EXPIRATION_TIME))
				.sign(algorithm);
		
	response.addHeader(ConstantsSecurity.HEADER_STRING, ConstantsSecurity.TOKEN_PREFIX + Jwt);
		//super.successfulAuthentication(request, response, chain, authResult);
	}

}
