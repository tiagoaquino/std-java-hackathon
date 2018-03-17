package com.skipthedishes.security;

import static com.skipthedishes.security.SecurityConstants.EXPIRATION_TIME;
import static com.skipthedishes.security.SecurityConstants.SECRET;
import static com.skipthedishes.security.SecurityConstants.TOKEN_PREFIX;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTBuilder {

	public String generate(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
	}
	
	public String parse(String token) {
		return Jwts.parser()
        .setSigningKey(SECRET.getBytes())
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
	}
	

}
