package com.skipthedishes.security;

import static com.skipthedishes.security.SecurityConstants.HEADER_STRING;
import static com.skipthedishes.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

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

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private JWTBuilder jwtBuilder;
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTBuilder jwtBuilder) {
		this.authenticationManager = authenticationManager;
		this.jwtBuilder = jwtBuilder;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String username = ((User) auth.getPrincipal()).getUsername();
		String token = jwtBuilder.generate(username);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}