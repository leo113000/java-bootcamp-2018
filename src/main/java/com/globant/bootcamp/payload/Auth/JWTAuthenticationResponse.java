package com.globant.bootcamp.payload.auth;

import lombok.Getter;
import lombok.Setter;

public class JWTAuthenticationResponse {
	@Getter @Setter private String accessToken;
	@Getter private static final String tokenType = "Bearer";

	public JWTAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
