package com.globant.bootcamp.controller;

import com.globant.bootcamp.payload.Auth.RegisterRequest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class) @SpringBootTest public class UserControllerTest {

	@Autowired UserController userController;

	@Autowired private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before public void setUp() {
		mockMvc = MockMvcBuilders
				// replace standaloneSetup with line below
				.webAppContextSetup(context).alwaysDo(print()).apply(SecurityMockMvcConfigurers.springSecurity()).build();

		RegisterRequest request = new RegisterRequest();
		request.setEmail("leo@yahoo.com");
		request.setUsername("leo");
		request.setPassword("1234");
		userController.registerUser(request);
	}

	@Test public void testRegister() throws Exception {
		this.mockMvc.perform(post("/register").param("email", "leox@yahoo.com").param("username", "leo2").param("password", "1234"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test public void testRegisterSameUsername() throws Exception {
		this.mockMvc.perform(post("/register").param("email", "leox@yahoo.com").param("username", "leo").param("password", "1234"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test public void testRegisterSameEmail() throws Exception {
		this.mockMvc.perform(post("/register").param("email", "leo@yahoo.com").param("username", "leo2").param("password", "1234"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test public void testLoginEndpoint() throws Exception {
		this.mockMvc.perform(post("/login").param("email", "leo@yahoo.com").param("username", "leo").param("password", "1234"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test public void testBadLogin() throws Exception {
		this.mockMvc.perform(post("/login").param("email", "leo@yahoo.com").param("username", "asda").param("password", "1234"))
				.andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test public void testGoodToken() throws Exception {
		String body = this.mockMvc
				.perform(post("/login").param("email", "leo@yahoo.com").param("username", "leo").param("password", "1234")).andReturn()
				.getResponse().getContentAsString();
		System.out.println(body);
		JSONObject jsonObject = new JSONObject(body);
		String token = jsonObject.getString("accessToken");
		this.mockMvc.perform(get("/cart").header("Authorization", "Bearer " + token).accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk());
	}

	@Test public void testBadToken() throws Exception {
		this.mockMvc.perform(get("/cart").header("Authorization", "Bearer " + "asdasd").accept("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError());

	}

	@Test public void badRequest() throws Exception {
		this.mockMvc.perform(post("/cart").param("email", "leo@yahoo.com").param("username", "asda").param("password", "1234"))
				.andDo(print()).andExpect(status().is4xxClientError());
	}
}
