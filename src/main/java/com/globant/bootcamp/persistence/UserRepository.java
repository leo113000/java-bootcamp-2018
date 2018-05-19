package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class UserRepository extends HashMap<Long, User> implements iRepository<Long, User> {

	public List<User> getAll() {
		return new ArrayList<>(this.values());
	}

	public User getById(Long id) {
		return this.get(id);
	}

	public User getByUsername(String username) {
		User result = null;
		for (User u : this.values()) {
			if (u.getUsername()!= null && u.getUsername().equals(username)) {
				result = u;
				break;
			}
		}
		return result;
	}

	public void removeById(Long id) {
		this.remove(id);
	}

	public User save(User user) {
		Long id = (long) this.size() + 1;
		user.setId(id);
		this.put(id, user);
		return user;
	}
}
