package com.globant.bootcamp.service;

import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class UserServiceTest {

	UserService userService;

	@Before public void contextLoads() {
		this.userService = new UserService(new UserRepository());
	}

	@Test public void whenCreateAnUserThenTheUserExistsInTheRepository() {
		assertTrue(this.userService.getAll().isEmpty());
		this.userService.register(mock(User.class));
		assertFalse(this.userService.getAll().isEmpty());
	}

	@Test public void whenGetByIdThenReturnTheRightUser() {
		Long id = (long) 2;
		User target = mock(User.class);
		this.userService.register(mock(User.class));
		when(target.getId()).thenReturn(id);
		this.userService.register(target);
		assertEquals(target.getId(), this.userService.getById(id).getId());
	}

	@Test public void whenGetByUsernameThenReturnTheRightUser() {
		String mockedUsername = "userMock";
		User u = mock(User.class);
		when(u.getUsername()).thenReturn(mockedUsername);
		this.userService.register(u);
		assertEquals(mockedUsername, this.userService.getByUsername(mockedUsername).getUsername());
	}

	@Test public void whenUpdateAnUserThenModifyTheUserInTheRepository() {
		Long id = (long) 2;
		User original = mock(User.class);
		User modified = mock(User.class);
		when(original.getId()).thenReturn(id);
		when(modified.getId()).thenReturn(id);
		when(original.getUsername()).thenReturn("original user");
		when(modified.getUsername()).thenReturn("modified user");
		this.userService.register(mock(User.class));
		this.userService.register(original);
		assertEquals("original user", this.userService.getById(id).getUsername());
		this.userService.updateById(id, modified);
		assertEquals("modified user", this.userService.getById(id).getUsername());
	}

	@Test public void whenRemoveAnUserThenThatUserIsDeletedFromTheRepository() {
		Long id = (long) 2;
		User target = mock(User.class);
		when(target.getId()).thenReturn(id);
		this.userService.register(mock(User.class));
		this.userService.register(target);
		assertTrue(this.userService.getAll().contains(target));
		this.userService.deleteById(id);
		assertFalse(this.userService.getAll().contains(target));
	}

}
