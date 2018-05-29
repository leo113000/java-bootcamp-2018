package com.globant.bootcamp.controller;

import com.globant.bootcamp.exception.ResourceNotFoundException;
import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) @SpringBootTest @AutoConfigureMockMvc public class ProductControllerTest {

	@MockBean private ProductService productService;

	@Autowired private MockMvc mockMvc;

	@Test public void testGetProducts() throws Exception {

		this.mockMvc.perform(get("/products")).andDo(print()).andExpect(status().isOk());

	}

	@Test public void testGetProductByName() throws Exception {

		Product p = new Product();
		p.setId((long) 1);
		p.setName("mock");
		p.setUrl("mock");
		p.setPrice(100);

		when(productService.getProductByUrl("mock")).thenReturn(p);
		this.mockMvc.perform(get("/products/mock")).andDo(print()).andExpect(status().isOk());
	}

	@Test public void testGetProductByNameException() throws Exception {
		when(productService.getProductByUrl("mock")).thenThrow(new ResourceNotFoundException());
		this.mockMvc.perform(get("/products/mock")).andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test public void testGetCategories() throws Exception {

		Category c = new Category();
		c.setId((long) 1);
		c.setName("mock");
		c.setUrl("mock");
		List<Category> cList = Arrays.asList(c);
		when(productService.getAllCategories()).thenReturn(cList);
		this.mockMvc.perform(get("/products/categories")).andDo(print()).andExpect(status().isOk());
	}

	@Test public void testGetProductsByCategory() throws Exception {
		Product p = new Product();
		p.setId((long) 1);
		p.setName("mock");
		p.setUrl("mock");
		p.setPrice(100);
		List<Product> pList = Arrays.asList(p);

		when(productService.getProductsByCategoryUrl("mock")).thenReturn(pList);
		this.mockMvc.perform(get("/products/categories/mock")).andDo(print()).andExpect(status().isOk());
	}

	@Test public void testGetProductsByCategoryException() throws Exception {
		when(productService.getProductsByCategoryUrl("mock")).thenThrow(new ResourceNotFoundException());
		this.mockMvc.perform(get("/products/categories/mock")).andDo(print()).andExpect(status().is4xxClientError());
	}
}
