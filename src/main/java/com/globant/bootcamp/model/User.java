package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter public class User {
	private Long id;
	private String name;
	private String email;
	private String username;
	private String password;

	public User(String email, String name,String username, String password) {
		this.email = email;
		this.name = name;
		this.username = username;
		this.password = password;
	}
}
