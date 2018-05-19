package com.globant.bootcamp.service;

import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
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

	/**
	 * Save the User in the Repository
	 * @param email
	 * @param username
	 * @param password
	 */
	public String register(String email, String name,String username,String password) {
		String result;

		if(getByUsername(username) != null){
			result = "The username is already taken";
		}else{
			this.userRepository.save(new User(email, name, username, password));
			result = "User Registered!";
		}

		return result;
	}

	/**
	 * @return a List with all the users in the repo
	 */
	public List<User> getAll() {
		return this.userRepository.getAll();
	}

	/**
	 * Retrieves an User by Id
	 *
	 * @param id
	 * @return User
	 */
	public User getById(Long id) {
		return this.userRepository.getById(id);
	}

	/**
	 * Retrieves an User by username
	 *
	 * @param username
	 * @return User
	 */
	public User getByUsername(String username) {
		return this.userRepository.getByUsername(username);
	}

	/**
	 * Retrieves an User by name
	 *
	 * @param name
	 * @return User
	 */
	public User getByName(String name) {
		return this.userRepository.getByName(name);
	}

	/**
	 * Updates an user by id
	 *
	 * @param id of the User
	 * @param u  the new User Model
	 */
	public void updateById(Long id, String email, String name,String username,String password) {
		this.userRepository.put(id, new User(email,name,username, password));
	}

	/**
	 * Deletes an user in the repo by id
	 *
	 * @param id of the User
	 */
	public void deleteById(Long id) {
		this.userRepository.removeById(id);
	}

}
