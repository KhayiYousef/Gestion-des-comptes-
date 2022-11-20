package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtAhorizationToken = request.getHeader(ConstantsSecurity.HEADER_STRING);
		if(jwtAhorizationToken != null && jwtAhorizationToken.startsWith(ConstantsSecurity.TOKEN_PREFIX)) {
			try {
				String jwt = jwtAhorizationToken.substring(7); 
				Algorithm algorithm = Algorithm.HMAC256(ConstantsSecurity.TOKEN_SECRET);
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT= jwtVerifier.verify(jwt);
				String username = decodedJWT.getSubject();
				
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(username, null,new ArrayList<>());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				filterChain.doFilter(request, response);
			}catch(Exception e) {
				response.setHeader("error message",e.getMessage());
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				
			}
			
		}
		else {
			filterChain.doFilter(request, response);
		}
		
		
	}

}
