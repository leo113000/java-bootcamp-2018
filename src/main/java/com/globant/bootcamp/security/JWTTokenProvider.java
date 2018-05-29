package com.globant.bootcamp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This class handles the generation and validation of JWT tokens
 */
@Component public class JWTTokenProvider {
	@Value("${jwt.secret}") private String jwtSecret;

	@Value("${jwt.expirationInMs}") private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {

		UserCredentials user = (UserCredentials) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder().setSubject(Long.toString(user.getId())).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		boolean result = false;
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			result = true;
		} catch (Exception e) {
			System.err.println("Error with token");
		}
		return result;
	}
}
