package com.globant.bootcamp.service;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.persistence.CartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class CartServiceTest {

	private CartService cartService;
	private final int MOCKED_PRODUCTS_QUANTITY = 3;

	@Before public void contextLoads() {
		CartRepository repo = new CartRepository();
		repo.add(new Cart("1"));
		repo.add(new Cart("2"));
		repo.add(this.getMockedCart("3"));
		cartService = new CartService(repo);
	}

	private Cart getMockedCart(String id) {

		Cart sp = new Cart(id);

		for (int i = 0; i < MOCKED_PRODUCTS_QUANTITY; i++) {
			Product m = mock(Product.class);
			sp.addProduct(m);
			when(m.getId()).thenReturn((long) i + 1);
			when(m.getName()).thenReturn("Play Station" + (i + 1));
		}
		return sp;
	}

	@Test public void whenGetAllProductsThenReturnAListOfProductsOfTheCorrectCart() {
		Cart cartWithElements = cartService.getShoppingCart("3");
		Cart emptyList = cartService.getShoppingCart("1");
		assertEquals(MOCKED_PRODUCTS_QUANTITY, cartWithElements.getAllProducts().size());
		assertEquals(0, emptyList.getAllProducts().size());
	}

	@Test public void whenAddAProductByIdThenSaveTheDesireProductInTheCorrectCart() {
		String id = "1";
		cartService.addProduct(mock(Product.class), id);
		assertEquals(1, cartService.getShoppingCart(id).getAllProducts().size());
	}

	@Test public void whenRemoveAProductByIdThenDeleteTheProductOfTheCorrectCart() {
		String id = "3";
		cartService.removeProduct(Long.parseLong(id), id);
		assertEquals(2, cartService.getShoppingCart(id).getAllProducts().size());
	}

	@Test public void whenEmptyACartThenRemoveAllTheElementsOfTheCorrectCart() {
		String id = "3";
		cartService.clearCart(id);
		assertTrue(cartService.getShoppingCart(id).getAllProducts().isEmpty());
	}
}
