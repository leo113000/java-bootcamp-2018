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

@RunWith(SpringRunner.class) @SpringBootTest public class ShoppingCartTest {
	private ShoppingCart shoppingCart;

	@Before public void contextLoads() {
		this.shoppingCart = new ShoppingCart("1");
	}

	@Test public void whenShoppingCartIsCreatedThenIsEmpty() {
		assertTrue(shoppingCart.getAllProducts().isEmpty());
	}

	@Test public void givenAShoppingCartWithProductsWhenGetAllTheProductsThenReturnAListOfProducts() {
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		shoppingCart.addProduct(a);
		shoppingCart.addProduct(b);

		Iterator<Product> iter = shoppingCart.getAllProducts().iterator();

		assertEquals(a, iter.next());
		assertEquals(b, iter.next());
	}

	@Test public void givenAShoppingCartWithProductsWhenISendTheIdThenReturnTheDesiredProduct() {
		Product a = mock(Product.class);
		shoppingCart.addProduct(a);
		Long id = (long) 1;
		when(a.getId()).thenReturn(id);
		assertEquals(a, shoppingCart.getProductById(id));
	}

	@Test public void givenACartWithProductsWhenCallEmptyMethodThenTheCartIsEmpty() {
		Product a = mock(Product.class);
		shoppingCart.addProduct(a);
		assertFalse(shoppingCart.getAllProducts().isEmpty());
		shoppingCart.clear();
		assertTrue(shoppingCart.getAllProducts().isEmpty());
	}

	@Test public void givenAShoppingCartWithProductsWhenISendTheIdThenDeleteFromTheListTheDesiredProduct() {
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		Long id = (long) 1;
		when(a.getId()).thenReturn(id);

		shoppingCart.addProduct(a);
		shoppingCart.addProduct(b);

		shoppingCart.removeProductById(id);

		assertEquals(1, shoppingCart.getAllProducts().size());
		assertFalse(shoppingCart.getAllProducts().contains(a));
	}

}
