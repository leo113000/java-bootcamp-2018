package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class) @SpringBootTest public class CartRepositoryTest {

	@Autowired CartRepository shoppingCart;

	@Test public void givenARepositoryWithElementsWhenGetsAllThenReturnsAListWithTheElements() {
		Cart sp = mock(Cart.class);
		shoppingCart.add(sp);
		assertEquals(1, shoppingCart.getAll().size());
	}

	@Test public void givenAnEmptyRepositoryWhenGetsAllThenReturnsAnEmptyList() {
		assertEquals(0, shoppingCart.getAll().size());
	}

}
