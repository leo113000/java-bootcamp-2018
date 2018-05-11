package com.globant.bootcamp.service;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.persistence.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class ShoppingCartServiceTest {

	private ShoppingCartService shoppingCartService;
	private final int MOCKED_PRODUCTS_QUANTITY = 3;

	@Before public void contextLoads() {
		ShoppingCartRepository repo = new ShoppingCartRepository();
		repo.add(new ShoppingCart("1"));
		repo.add(new ShoppingCart("2"));
		repo.add(this.getMockedCart("3"));
		shoppingCartService = new ShoppingCartService(repo);
	}

	private ShoppingCart getMockedCart(String id) {

		ShoppingCart sp = new ShoppingCart(id);

		for (int i = 0; i < MOCKED_PRODUCTS_QUANTITY; i++) {
			Product m = mock(Product.class);
			sp.addProduct(m);
			when(m.getId()).thenReturn((long) i + 1);
			when(m.getName()).thenReturn("Play Station" + (i + 1));
		}
		return sp;
	}

	@Test public void whenGetAllProductsThenReturnAListOfProductsOfTheCorrectCart() {
		ShoppingCart shoppingCartWithElements = shoppingCartService.getShoppingCart("3");
		ShoppingCart emptyList = shoppingCartService.getShoppingCart("1");
		assertEquals(MOCKED_PRODUCTS_QUANTITY, shoppingCartWithElements.getAllProducts().size());
		assertEquals(0, emptyList.getAllProducts().size());
	}

	@Test public void whenAddAProductByIdThenSaveTheDesireProductInTheCorrectCart() {
		String id = "1";
		shoppingCartService.addProduct(mock(Product.class), id);
		assertEquals(1, shoppingCartService.getShoppingCart(id).getAllProducts().size());
	}

	@Test public void whenRemoveAProductByIdThenDeleteTheProductOfTheCorrectCart() {
		String id = "3";
		shoppingCartService.removeProduct(Long.parseLong(id), id);
		assertEquals(2, shoppingCartService.getShoppingCart(id).getAllProducts().size());
	}

	@Test public void whenEmptyACartThenRemoveAllTheElementsOfTheCorrectCart() {
		String id = "3";
		shoppingCartService.clearCart(id);
		assertTrue(shoppingCartService.getShoppingCart(id).getAllProducts().isEmpty());
	}
}
