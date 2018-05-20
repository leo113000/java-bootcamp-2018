package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username")String username);

	@Query("select u from User u where u.email = :email")
	User findByEmail(@Param("email")String email);

}
