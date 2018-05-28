package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.*;
import com.globant.bootcamp.payload.Auth.RegisterRequest;
import com.globant.bootcamp.persistence.DeliverMethodRepository;
import com.globant.bootcamp.persistence.PaymentMethodRepository;
import com.globant.bootcamp.persistence.ProductRepository;
import com.globant.bootcamp.persistence.StatusRepository;
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

@RunWith(SpringRunner.class) @SpringBootTest public class OrderControllerTest {

	@Autowired
	private UserController userController;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	@Autowired
	private DeliverMethodRepository deliverMethodRepository;
	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private String token;



	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				// replace standaloneSetup with line below
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
			this.register("leo@yahoo.com", "leo", "1234");
			this.token = "Bearer " + this.login("leo", "1234");
	}

	@Test
	public void contextLoads(){
		assertNotNull(this.token);
	}


	@Test public void testGetEndpoint() throws Exception {
		this.mockMvc.perform(get("/orders")
				.header("Authorization", token)
				.accept("application/json;charset=UTF-8"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test public void testCreateOrder() throws Exception {
		this.saveAProductIntoTheCart();
		this.savePaymentAndDeliverMethods();
		this.mockMvc.perform(post("/orders")
				.header("Authorization", this.token)
				.param("paymentMethodId","1")
				.param("deliverMethodId","1")
				.accept("application/json;charset=UTF-8"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test public void testWrongParametersCreateOrder() throws Exception {
		this.saveAProductIntoTheCart();
		this.mockMvc.perform(post("/orders")
				.header("Authorization", this.token)
				.param("paymentMethodId","155")
				.param("deliverMethodId","15")
				.accept("application/json;charset=UTF-8"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test public void testCartEmptyCreateOrder() throws Exception {

		this.mockMvc.perform(post("/orders")
				.header("Authorization", this.token)
				.param("paymentMethodId","1")
				.param("deliverMethodId","1")
				.accept("application/json;charset=UTF-8"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	private void register(String email, String username, String password){
		RegisterRequest request = new RegisterRequest();
		request.setEmail(email);
		request.setUsername(username);
		request.setPassword(password);
		userController.registerUser(request);
	}

	private String login(String username, String password) throws Exception {
		String body = this.mockMvc.perform(post("/login")
				.param("username",username)
				.param("password",password))
				.andReturn().getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(body);
		return jsonObject.getString("accessToken");
	}

	private void saveAProductIntoTheCart() throws Exception {
		Product p = new Product();
		p.setName("product");
		p.setUrl("some-product");
		p.setPrice(100);
		productRepository.save(p);

		this.mockMvc.perform(post("/cart")
				.header("Authorization", this.token)
				.param("productId","1")
				.param("qty","1")
				.accept("application/json;charset=UTF-8"));
	}

	private void savePaymentAndDeliverMethods(){
		PaymentMethod pm = new PaymentMethod();
		DeliverMethod dm = new DeliverMethod();
		Status status = new Status();

		pm.setName("mockpay");
		dm.setName("mockdeliver");
		status.setName("mockstatus");

		this.paymentMethodRepository.save(pm);
		this.deliverMethodRepository.save(dm);
		this.statusRepository.save(status);
	}

}
