package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AuthRepository extends HashMap <String,User> {

	public void login(String token, User user){
		this.put(token,user);
	}

	public void logout(String token){
		this.remove(token);
	}

}
