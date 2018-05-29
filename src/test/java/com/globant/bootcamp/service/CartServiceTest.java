package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.ResourceNotFoundException;
import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.model.CartLine;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.CartRepository;
import com.globant.bootcamp.persistence.ProductRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class CartServiceTest {

	private CartService service;

	private CartRepository cartRepository;

	private ProductRepository productRepository;

	private User testUser;
	private Cart cart;
	private Product testProduct;

	@Before public void contextLoads() throws Exception {
		testUser = new User("mockman@test.com", "mockman", "1234");
		testUser.setId((long) 1);
		cart = new Cart(testUser);
		this.cartRepository = mock(CartRepository.class);
		this.productRepository = mock(ProductRepository.class);
		this.service = new CartService(cartRepository, productRepository);
		when(cartRepository.findByUserId(testUser.getId())).thenReturn(cart);
		when(cartRepository.save(cart)).thenReturn(cart);
		testProduct = mock(Product.class);
		when(testProduct.getId()).thenReturn((long) 1);
		when(testProduct.getName()).thenReturn("productMock");
		when(productRepository.findById((long) 1)).thenReturn(Optional.ofNullable(testProduct));
	}

	@Test public void whenGetShoppingCartThenReturnAShoppingCart() {
		assertNotNull(this.service.getCart(testUser));
	}

	@Test public void whenAddAProductThenReturnShoppingCartWithTheNewProduct() {
		this.service.addProduct(testProduct.getId(), 10, testUser);
		Optional<CartLine> found = this.service.getCart(testUser).getProductList().stream()
				.filter(x -> x.getProduct().getName().equals("productMock")).findFirst();
		assertTrue(found.isPresent());
	}

	@Test public void whenUpdateAProductThenReturnShoppingCartWithTheUpdateProduct() {
		this.service.addProduct(testProduct.getId(), 10, testUser);
		this.service.updateLineProduct(testProduct.getId(), 5, testUser);
		Optional<CartLine> found = this.service.getCart(testUser).getProductList().stream()
				.filter(x -> x.getProduct().getName().equals("productMock")).findFirst();
		assertEquals(5, found.get().getQty());
	}

	@Test public void whenRemoveAProductThenReturnShoppingCartWithoutThatProduct() {
		this.service.addProduct(testProduct.getId(), 10, testUser);
		Optional<CartLine> found = this.service.getCart(testUser).getProductList().stream()
				.filter(x -> x.getProduct().getName().equals("productMock")).findFirst();
		TestCase.assertTrue(found.isPresent());
		this.service.removeProduct(found.get().getProduct().getId(), testUser);
		found = this.service.getCart(testUser).getProductList().stream().filter(x -> x.getProduct().getName().equals("productMock"))
				.findFirst();
		assertFalse(found.isPresent());
	}

	@Test public void whenDeleteShoppingCartThenEmptyShoppingCart() {
		this.service.clearCart(testUser);
		assertTrue(service.getCart(testUser).getProductList().isEmpty());
	}

	@Test(expected = ResourceNotFoundException.class) public void whenAddAProductThatDoesntExistsThenThrowAnException() {
		this.service.addProduct((long) 44541, 10, testUser);
	}

	@Test(expected = ResourceNotFoundException.class) public void whenUpdateAProductThatDoesntExistsThenThrowAnException() {
		this.service.addProduct((long) 44541, 10, testUser);
	}

}
