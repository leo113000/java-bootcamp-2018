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
		User aMock = mock(User.class);
		this.userService.register(aMock.getEmail(), aMock.getName(), aMock.getUsername(),aMock.getPassword());
		assertFalse(this.userService.getAll().isEmpty());
	}

	@Test public void whenGetByIdThenReturnTheRightUser() {
		Long id = (long) 2;
		User target = mock(User.class);
		User anotherMock = mock(User.class);
		when(target.getUsername()).thenReturn("anotherMocky");
		this.userService.register(anotherMock.getEmail(), anotherMock.getName(), anotherMock.getUsername(),anotherMock.getPassword());
		when(target.getId()).thenReturn(id);
		when(target.getUsername()).thenReturn("targetMocky");
		this.userService.register(target.getEmail(), target.getName(), target.getUsername(),target.getPassword());
		assertEquals(target.getId(), this.userService.getById(id).getId());
	}

	@Test public void whenGetByUsernameThenReturnTheRightUser() {
		String mockedUsername = "userMock";
		User u = mock(User.class);
		when(u.getUsername()).thenReturn(mockedUsername);
		this.userService.register(u.getEmail(), u.getName(), u.getUsername(),u.getPassword());
		assertEquals(mockedUsername, this.userService.getByUsername(mockedUsername).getUsername());
	}

	@Test public void whenUpdateAnUserThenModifyTheUserInTheRepository() {
		Long id = (long) 2;
		User original = mock(User.class);
		User modified = mock(User.class);
		User anotherMock = mock(User.class);
		when(original.getId()).thenReturn(id);
		when(modified.getId()).thenReturn(id);
		when(original.getUsername()).thenReturn("original user");
		when(modified.getUsername()).thenReturn("modified user");
		this.userService.register(anotherMock.getEmail(), anotherMock.getName(), anotherMock.getUsername(),anotherMock.getPassword());
		this.userService.register(original.getEmail(), original.getName(),original.getUsername(),original.getPassword());
		assertEquals("original user", this.userService.getById(id).getUsername());
		this.userService.updateById(id, modified.getEmail(),modified.getName(),modified.getUsername(),modified.getPassword());
		assertEquals("modified user", this.userService.getById(id).getUsername());
	}

	@Test public void whenRemoveAnUserThenThatUserIsDeletedFromTheRepository() {
		Long id = (long) 2;
		User target = mock(User.class);
		when(target.getId()).thenReturn(id);
		User anotherMock = mock(User.class);
		this.userService.register(anotherMock.getEmail(), anotherMock.getName(),anotherMock.getUsername(),anotherMock.getPassword());
		this.userService.register(target.getEmail(),target.getName(), target.getUsername(),target.getPassword());
		target = this.userService.getById(id);
		assertTrue(this.userService.getAll().contains(target));
		this.userService.deleteById(id);
		assertFalse(this.userService.getAll().contains(target));
	}

}
