package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.ResourceNotFoundException;
import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.persistence.CategoryRepository;
import com.globant.bootcamp.persistence.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @SpringBootTest public class ProductServiceTest {

	private ProductService productService;

	private ProductRepository mockedProductRepo;
	private CategoryRepository mockedCategoryRepo;

	@Before public void setUp() {
		this.mockedProductRepo = mock(ProductRepository.class);
		this.mockedCategoryRepo = mock(CategoryRepository.class);
		Product p = mock(Product.class);
		Category c = mock(Category.class);
		when(c.getName()).thenReturn("cat-url");
		when(c.getUrl()).thenReturn("cat-url");
		when(p.getUrl()).thenReturn("the-url");
		when(mockedProductRepo.findByUrl("the-url")).thenReturn(Optional.ofNullable(p));
		when(mockedCategoryRepo.findByUrl("cat-url")).thenReturn(Optional.ofNullable(c));
		when(mockedProductRepo.findAll()).thenReturn(new ArrayList<>());
		when(mockedCategoryRepo.findAll()).thenReturn(new ArrayList<>());
		when(mockedProductRepo.findByCategories(c)).thenReturn(new ArrayList<>());
		this.productService = new ProductService(mockedProductRepo,mockedCategoryRepo);
	}

	@Test
	public void whenGetAllProductsReturnAListOfProducts(){
		assertNotNull(this.productService.getAllProducts());
	}

	@Test
	public void whenGetAllCategoriesThenReturnAListOfCategories(){
		assertNotNull(this.productService.getAllCategories());
	}

	@Test
	public void whenSearchByNameThenReturnAProduct(){
		assertEquals("the-url",this.productService.getProductByUrl("the-url").getUrl());
	}

	@Test
	public void whenSearchByCategoryThenReturnAListOfProducts(){
		assertNotNull(this.productService.getProductsByCategoryUrl("cat-url"));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void whenSearchByNameAProductThatDoesntExistsThenThrowAnException(){
		this.productService.getProductByUrl("url-that-not-exists");
	}

	@Test(expected = ResourceNotFoundException.class)
	public void whenSearchByACategoryThatDoesntExistsThenThrowAnException(){
		this.productService.getProductsByCategoryUrl("url-that-not-exists");
	}


}
