package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class) @SpringBootTest public class ShoppingCartRepositoryTest {

	@Autowired ShoppingCartRepository shoppingCart;

	@Test public void givenARepositoryWithElementsWhenGetsAllThenReturnsAListWithTheElements() {
		ShoppingCart sp = mock(ShoppingCart.class);
		shoppingCart.add(sp);
		assertEquals(1, shoppingCart.getAll().size());
	}

	@Test public void givenAnEmptyRepositoryWhenGetsAllThenReturnsAnEmptyList() {
		assertEquals(0, shoppingCart.getAll().size());
	}

}
