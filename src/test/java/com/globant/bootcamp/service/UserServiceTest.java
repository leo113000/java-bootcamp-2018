package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.ExistingEmailException;
import com.globant.bootcamp.exception.ExistingUsernameException;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class) @SpringBootTest public class UserServiceTest {

	@Autowired private UserService userService;

	@Autowired private UserRepository userRepository;

	private User mockedUser1;
	private User mockedUser2;
	private User mockedUser3;

	@Before public void contextLoads() {
		this.mockedUser1 = new User("email@hotmail.com", "hotman", "1234");
		this.mockedUser2 = new User("email@gmail.com", "gman", "1234");
		this.mockedUser3 = new User("email@yahoo.com.mx", "yaman", "1234");
	}

	@Test public void whenRegisterAnUserThenTheUserExistsInTheRepository() throws ExistingUsernameException, ExistingEmailException {
		this.userService.register(this.mockedUser2.getEmail(), this.mockedUser2.getUsername(), this.mockedUser2.getPassword());
		assertEquals(this.mockedUser2.getUsername(), this.userService.loadUserByUsername(this.mockedUser2.getUsername()).getUsername());
	}

	@Test(expected = ExistingUsernameException.class) public void whenTryToRegisterAnUserWithAnExisitingUsernameThenThrowAnException()
			throws ExistingUsernameException, ExistingEmailException {
		this.userService.register(this.mockedUser1.getEmail(), this.mockedUser1.getUsername(), this.mockedUser1.getPassword());
		this.userService.register("another@email.com", this.mockedUser1.getUsername(), this.mockedUser1.getPassword());
	}

	@Test(expected = ExistingEmailException.class) public void whenTryToRegisterAnUserWithAnExisitingEmailThenThrowAnException()
			throws ExistingUsernameException, ExistingEmailException {
		this.userService.register(this.mockedUser1.getEmail(), "anotherUsername", this.mockedUser1.getPassword());
		this.userService.register(this.mockedUser1.getEmail(), this.mockedUser1.getUsername(), this.mockedUser1.getPassword());
	}

	@Test public void whenGetByIdThenReturnTheRightUser() throws ExistingUsernameException, ExistingEmailException {
		this.userService.register("another@email.com", "anotherUsername", this.mockedUser1.getPassword());
		this.userService.register(this.mockedUser3.getEmail(), this.mockedUser3.getUsername(), this.mockedUser3.getPassword());
		this.userService.register("tony@stark.com", "ironman", "1234");
		Long id = this.userRepository.findByUsernameOrEmail(mockedUser3.getUsername(), mockedUser3.getEmail()).get().getId();
		assertEquals(this.mockedUser3.getUsername(), this.userService.loadUserById(id).getUsername());
	}

	@Test public void whenGetByUsernameThenReturnTheRightUser() throws ExistingUsernameException, ExistingEmailException {
		this.userService.register(this.mockedUser1.getEmail(), this.mockedUser1.getUsername(), this.mockedUser1.getPassword());
		assertEquals(this.mockedUser1.getUsername(), this.userService.loadUserByUsername(this.mockedUser1.getUsername()).getUsername());
	}

	@Test(expected = UsernameNotFoundException.class) public void whenGetByIdAnUserWhoDontExistThenReturnAnException() {
		// A big number that doesn't exists in the db
		Long id = (long) 123574845;
		this.userService.loadUserById(id);
	}

	@Test(expected = UsernameNotFoundException.class) public void whenGetByUsernameAnUserWhoDontExistThenReturnAnException() {
		this.userService.loadUserByUsername("zaparticoinmatico");
	}

}
