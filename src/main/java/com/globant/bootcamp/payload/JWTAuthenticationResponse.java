package com.globant.bootcamp.payload;

import lombok.Getter;
import lombok.Setter;

public class JWTAuthenticationResponse{
	@Getter @Setter private String accessToken;
	@Getter private String tokenType = "Bearer";

	public JWTAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
