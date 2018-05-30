package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.payload.auth.RegisterRequest;
import com.globant.bootcamp.persistence.ProductRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) @SpringBootTest public class CartControllerTest {

	@Autowired UserController userController;

	@Autowired private WebApplicationContext context;

	@Autowired private ProductRepository productRepository;

	private MockMvc mockMvc;

	private String token;

	@Before public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				// replace standaloneSetup with line below
				.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
		this.saveAProduct();
		this.register("leo@yahoo.com", "leo", "1234");
		this.token = "Bearer " + this.login("leo", "1234");
	}

	@Test public void contextLoads() {
		assertNotNull(this.token);
	}

	@Test public void testGetEndpoint() throws Exception {
		this.mockMvc.perform(get("/cart").header("Authorization", token).accept("application/json;charset=UTF-8")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test public void testAddProductToCart() throws Exception {
		this.mockMvc.perform(post("/cart").header("Authorization", this.token).param("productId", "1").param("qty", "1")
				.accept("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk());
	}

	@Test public void testUpdateProductOfTheCart() throws Exception {
		this.mockMvc.perform(put("/cart").header("Authorization", this.token).param("productId", "1").param("qty", "40")
				.accept("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk());
	}

	@Test public void whenRemoveAProductThenReturnShoppingCartWithoutThatProduct() throws Exception {
		this.mockMvc.perform(delete("/cart/1").header("Authorization", this.token).accept("application/json;charset=UTF-8")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test public void whenDeleteShoppingCartThenEmptyShoppingCart() throws Exception {
		this.mockMvc.perform(delete("/cart").header("Authorization", this.token).accept("application/json;charset=UTF-8")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test() public void whenAddAProductThatDoesntExistsThenThrowAnException() throws Exception {
		this.mockMvc.perform(post("/cart").header("Authorization", this.token).param("productId", "60").param("qty", "1")
				.accept("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test() public void whenUpdateAProductThatDoesntExistsThenThrowAnException() throws Exception {
		this.mockMvc.perform(put("/cart").header("Authorization", this.token).param("productId", "40").param("qty", "40")
				.accept("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isNotFound());
	}

	private void register(String email, String username, String password) {
		RegisterRequest request = new RegisterRequest();
		request.setEmail(email);
		request.setUsername(username);
		request.setPassword(password);
		userController.registerUser(request);
	}

	private String login(String username, String password) throws Exception {
		String body = this.mockMvc.perform(post("/login").param("username", username).param("password", password)).andReturn().getResponse()
				.getContentAsString();
		JSONObject jsonObject = new JSONObject(body);
		return jsonObject.getString("accessToken");
	}

	private void saveAProduct() {
		Product p = new Product();
		p.setName("product");
		p.setUrl("some-product");
		p.setPrice(100);
		productRepository.save(p);
	}

}
