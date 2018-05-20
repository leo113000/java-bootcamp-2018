package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "users") public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
	@Column(name = "email") private String email;
	@Column(name = "username") private String username;
	@Column(name = "password") private String password;

	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
