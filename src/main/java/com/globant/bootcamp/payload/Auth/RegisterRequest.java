package com.globant.bootcamp.payload.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;
	@NotBlank
	@Size(max = 40)
	private String username;
	@NotBlank
	@Size(max = 40)
	private String password;
}
