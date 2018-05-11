package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter public class User {
	private Long id;
	private String email;
	private String username;
	private String password;
}
