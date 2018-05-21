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
		this.cart = new Cart(mock(User.class));
	}

	@Test public void whenShoppingCartIsCreatedThenIsEmpty() {
		assertTrue(cart.getProductList().isEmpty());
	}

	@Test public void givenAShoppingCartWithProductsWhenGetAllTheProductsThenReturnAListOfProducts() {
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		cart.addProduct(a,1);
		cart.addProduct(b,4);

		Iterator<ProductLine> iter = cart.getProductList().iterator();
		ProductLine lineA = iter.next();
		ProductLine lineB = iter.next();
		assertEquals(a,lineA.getProduct());
		assertEquals(1, lineA.getQty());
		assertEquals(b, lineB.getProduct());
		assertEquals(4, lineB.getQty());
	}

	@Test public void givenACartWithProductsWhenCallEmptyMethodThenTheCartIsEmpty() {
		Product a = mock(Product.class);
		cart.addProduct(a,45);
		assertFalse(cart.getProductList().isEmpty());
		cart.clear();
		assertTrue(cart.getProductList().isEmpty());
	}

	@Test public void givenAShoppingCartWithProductsWhenISendTheIdThenDeleteFromTheListTheDesiredProduct() {
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		Long id = (long) 1;
		when(a.getId()).thenReturn(id);

		cart.addProduct(a,21);
		cart.addProduct(b,21);

		cart.removeProductById(id);

		assertEquals(1, cart.getProductList().size());
		assertNull(cart.getProductList().stream().filter(x ->
				x.getProduct().equals(a))
				.findFirst().orElse(null));
	}

	@Test
	public void givenACartWithElementsWhenGetTheTotalThenReturnTheAdditionOfAllTheProductLines(){
		Product a = mock(Product.class);
		Product b = mock(Product.class);

		when(a.getPrice()).thenReturn(10.0);
		when(b.getPrice()).thenReturn(10.0);

		cart.addProduct(a,10);
		cart.addProduct(b,10);

		cart.getTotal();

	}

}
