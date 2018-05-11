package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class UserRepositoryTest {

	UserRepository userRepository;

	@Before public void contextLoads() {
		this.userRepository = new UserRepository();
	}

	@Test public void givenAnEmptyRepositoryThenTheSizeIsZero() {
		assertTrue(this.userRepository.isEmpty());
	}

	@Test public void givenAnEmptyRepositoryWhenAddOneUserThenTheSizeIsOne() {
		User u = mock(User.class);
		this.userRepository.save(u);
		assertEquals(1, this.userRepository.size());
	}

	@Test public void givenARepositoryWithElementsWhenGetByIdThenReturnsTheRightUser() {
		Long id = (long) 1;
		User u = mock(User.class);
		when(u.getId()).thenReturn(id);
		this.userRepository.save(u);
		assertEquals(id, this.userRepository.getById(id).getId());
	}

	@Test public void givenARepositoryWithElementsWhenGetByUsernameThenReturnsTheRightUser() {
		String username = "mock user";
		User u = mock(User.class);
		when(u.getUsername()).thenReturn(username);
		this.userRepository.save(u);
		assertEquals(username, this.userRepository.getByUsername(username).getUsername());
	}

	@Test public void givenARepositoryWithElementsWhenGetAllThenReturnAListWithTheElements() {
		final int elementsQty = 3;
		for (int i = 0; i < elementsQty; i++) {
			this.userRepository.save(mock(User.class));
		}
		assertEquals(elementsQty, this.userRepository.getAll().size());
	}

	@Test public void givenARepositoryWithElementsWhenRemoveByIdThenRemoveTheRightElement() {
		Long id = (long) 2;

		User toRemove = mock(User.class);
		User toKeep = mock(User.class);

		when(toKeep.getId()).thenReturn(id - 1);
		when(toRemove.getId()).thenReturn(id);

		this.userRepository.save(toKeep);
		this.userRepository.save(toRemove);
		this.userRepository.removeById(id);

		assertEquals(1, this.userRepository.size());
		assertFalse(this.userRepository.containsValue(toRemove));
	}

}
