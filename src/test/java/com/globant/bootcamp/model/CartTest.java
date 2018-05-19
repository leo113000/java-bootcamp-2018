package com.globant.bootcamp.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class CartTest {
	private Cart cart;

	@Before public void contextLoads() {
		this.cart = new Cart("1");
	}

	@Test public void whenShoppingCartIsCreatedThenIsEmpty() {
		assertTrue(cart.getAllProducts().isEmpty());
	}

	@Test public void givenAShoppingCartWithProductsWhenGetAllTheProductsThenReturnAListOfProducts() {
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		cart.addProduct(a);
		cart.addProduct(b);

		Iterator<Product> iter = cart.getAllProducts().iterator();

		assertEquals(a, iter.next());
		assertEquals(b, iter.next());
	}

	@Test public void givenAShoppingCartWithProductsWhenISendTheIdThenReturnTheDesiredProduct() {
		Product a = mock(Product.class);
		cart.addProduct(a);
		Long id = (long) 1;
		when(a.getId()).thenReturn(id);
		assertEquals(a, cart.getProductById(id));
	}

	@Test public void givenACartWithProductsWhenCallEmptyMethodThenTheCartIsEmpty() {
		Product a = mock(Product.class);
		cart.addProduct(a);
		assertFalse(cart.getAllProducts().isEmpty());
		cart.clear();
		assertTrue(cart.getAllProducts().isEmpty());
	}

	@Test public void givenAShoppingCartWithProductsWhenISendTheIdThenDeleteFromTheListTheDesiredProduct() {
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		Long id = (long) 1;
		when(a.getId()).thenReturn(id);

		cart.addProduct(a);
		cart.addProduct(b);

		cart.removeProductById(id);

		assertEquals(1, cart.getAllProducts().size());
		assertFalse(cart.getAllProducts().contains(a));
	}

}
