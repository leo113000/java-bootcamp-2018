package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.persistence.ShoppingCartRepository;
import com.globant.bootcamp.service.ShoppingCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class ShoppingCartControllerTest {

	private ShoppingCartController controller;
	@Autowired private MockHttpSession mockHttpSession;

	@Before public void contextLoads() {
		ShoppingCartService service = new ShoppingCartService(this.mockRepository());
		this.controller = new ShoppingCartController(service);
	}

	private ShoppingCartRepository mockRepository() {
		ShoppingCartRepository repo = new ShoppingCartRepository();

		ShoppingCart sp1 = new ShoppingCart(mockHttpSession.getId());
		ShoppingCart sp2 = new ShoppingCart(mockHttpSession.getId() + 1);

		Product a = mock(Product.class);
		when(a.getId()).thenReturn((long) 1);
		when(a.getName()).thenReturn("Tv");
		sp1.addProduct(a);

		Product b = mock(Product.class);
		when(b.getId()).thenReturn((long) 2);
		when(b.getName()).thenReturn("Tablet");
		sp1.addProduct(b);

		repo.add(sp1);
		repo.add(sp2);

		return repo;
	}

	@Test public void whenGetShoppingCartThenReturnAShoppingCart() {
		ShoppingCart sp = this.controller.getShoppingCartBySessionId();
		assertEquals(2, sp.getAllProducts().size());
	}

	@Test public void whenDeleteShoppingCartThenEmptyShoppingCart() {
		ShoppingCart sp = this.controller.getShoppingCartBySessionId();
		this.controller.emptyShoppingCart();
		assertTrue(sp.getAllProducts().isEmpty());
	}

	@Test public void whenAddAProductThenReturnShoppingCartWithTheNewProduct() {
		Product p = mock(Product.class);
		Long productId = (long) 12;
		when(p.getId()).thenReturn(productId);
		when(p.getName()).thenReturn("mocked product");
		this.controller.addProduct(p.getId(),p.getName());
		ShoppingCart sp = this.controller.getShoppingCartBySessionId();
		assertEquals("mocked product", this.controller.getShoppingCartBySessionId().getProductById(productId).getName());
	}

	@Test public void whenRemoveAProductThenReturnShoppingCartWithoutThatProduct() {
		Product p = mock(Product.class);
		Long productId = (long) 1;
		when(p.getId()).thenReturn(productId);
		assertEquals(p.getId(), this.controller.getShoppingCartBySessionId().getProductById((long) 1).getId());
		this.controller.removeProduct(p.getId());
		assertEquals(null, this.controller.getShoppingCartBySessionId().getProductById((long) 1));
	}

}
