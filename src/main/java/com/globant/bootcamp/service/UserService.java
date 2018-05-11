package com.globant.bootcamp.service;

import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

	@Autowired UserRepository userRepository;

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
	 *
	 * @param user the user's model to be saved
	 */
	public void register(User user) {
		this.userRepository.save(user);
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
	 * Updates an user by id
	 *
	 * @param id of the User
	 * @param u  the new User Model
	 */
	public void updateById(Long id, User u) {
		this.userRepository.put(id, u);
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
