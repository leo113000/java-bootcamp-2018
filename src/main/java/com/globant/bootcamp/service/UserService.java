package com.globant.bootcamp.service;

import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.AuthRepository;
import com.globant.bootcamp.persistence.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
	@Autowired private UserRepository userRepository;

	/**
	 * Constructor with one param
	 *
	 * @param userRepository
	 */
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String login(String username,String password) {
		String token = null;

		User u = getByUsername(username);

		if(u != null && password.equals(decrypt(u.getPassword()))) {
			token = registerToken(u);
		}

		return token;
	}

	private String registerToken(User user) {
		String token = encrypt(user.getUsername() + user.getPassword());
		AuthRepository.getInstance().login(token,user);
		return token;
	}

	/**
	 * Save the User in the Repository
	 * @param email
	 * @param username
	 * @param password
	 */
	public String register(String email, String username,String password) {
		String result;

		if(getByUsername(username) != null){
			result = "The username is already taken";
		}else if(getByEmail(email) != null) {
			result = "Already exists an user with that email";
		}else{
			String hashedPassword = encrypt(password);
			this.userRepository.save(new User(email, username, hashedPassword));
			result = "User Registered!";
		}
		return result;
	}

	public void logout(String token){
		AuthRepository.getInstance().logout(token);
	}


	/**
	 * Retrieves an User by username
	 *
	 * @param username
	 * @return User
	 */
	public User getByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	/**
	 * Retrieves an User by email
	 *
	 * @param email
	 * @return User
	 */
	public User getByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	private String encrypt(String s){
		return new String (Base64.encodeBase64(s.getBytes()));
	}

	private String decrypt(String s){
		return new String (Base64.decodeBase64(s.getBytes()));
	}

}
