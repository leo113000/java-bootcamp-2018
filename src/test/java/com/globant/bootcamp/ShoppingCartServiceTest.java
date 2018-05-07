package com.globant.bootcamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceTest {

	ShoppingCartService shoppingCartService;
	final int MOCKED_PRODUCTS_QUANTITY = 3;
	@Before
	public void contextLoads(){
		ShoppingCartRepository repo = new ShoppingCartRepository();
		repo.add(new ShoppingCart((long)1));
		repo.add(new ShoppingCart((long)2));
		repo.add(this.getMockedCart((long)3));
		shoppingCartService = new ShoppingCartService(repo);
	}

	private ShoppingCart getMockedCart(long id) {

		List <Product> products = new ArrayList<>();

		for(int i = 0; i<MOCKED_PRODUCTS_QUANTITY ; i++){
			Product m = mock(Product.class);
			products.add(m);
			when(m.getId()).thenReturn((long)i+1);
			when(m.getName()).thenReturn("Play Station" + (i+1));
		}

		ShoppingCart sp = mock(ShoppingCart.class);
		when(sp.getAllProducts()).thenReturn(products);

		return sp;
	}

	@Test
	public void whenGetAllProductsThenReturnAListOfProductsOfTheCorrectCart(){
		List producListWithElements = shoppingCartService.getProducts((long)3);
		List emptyList = shoppingCartService.getProducts((long)1);
		assertEquals(MOCKED_PRODUCTS_QUANTITY,producListWithElements.size());
		assertEquals(0,emptyList.size());
	}

	@Test
	public void  whenAddAProductByIdThenSaveTheDesireProductInTheCorrectCart(){
		long id = 1;
		shoppingCartService.addProduct(mock(Product.class),id);
		assertEquals(1,shoppingCartService.getProducts(id).size());
	}

	@Test
	public void whenRemoveAProductByIdThenDeleteTheProductOfTheCorrectCart(){
		long id = 3;
		shoppingCartService.removeProduct(id,id);
		assertEquals(2,shoppingCartService.getProducts(id).size());
	}

	@Test
	public void whenEmptyACartThenRemoveAllTheElementsOfTheCorrectCart(){
		long id = 3;
		shoppingCartService.clearCart(id);
		assertTrue(shoppingCartService.getProducts(id).isEmpty());
	}
}
