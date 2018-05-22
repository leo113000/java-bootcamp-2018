package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository public interface UserRepository extends JpaRepository<User, Long> {
	/*
	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username")String username);

	@Query("select u from User u where u.email = :email")
	User findByEmail(@Param("email")String email);
	*/

	Optional<User> findByUsernameOrEmail(String username, String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
