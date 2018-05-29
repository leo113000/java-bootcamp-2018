package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.ExistingEmailException;
import com.globant.bootcamp.exception.ExistingUsernameException;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.UserRepository;
import com.globant.bootcamp.security.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service public class UserService implements UserDetailsService {

	@Autowired UserRepository userRepository;

	@Autowired PasswordEncoder passwordEncoder;

	@Override @Transactional public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// Let people login with either username or email
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return UserCredentials.create(user);
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with that id"));
		return UserCredentials.create(user);
	}

	public void register(String email, String username, String password) throws ExistingEmailException, ExistingUsernameException {
		if (userRepository.existsByUsername(username)) {
			throw new ExistingUsernameException();
		}

		if (userRepository.existsByEmail(email)) {
			throw new ExistingEmailException();
		}

		User user = userRepository.save(new User(email, username, passwordEncoder.encode(password)));
	}
}