package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.model.Order;
import com.globant.bootcamp.security.CurrentUser;
import com.globant.bootcamp.security.UserCredentials;
import com.globant.bootcamp.service.CartService;
import com.globant.bootcamp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired private CartService cartService;
	@Autowired private OrderService orderService;

	public OrderController(CartService cartService, OrderService orderService) {
		this.cartService = cartService;
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Order> getOrders(HttpServletResponse response) {
		return this.orderService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Order createOrder
			(@CurrentUser UserCredentials currentUser,@RequestParam Long paymentMethodId, @RequestParam Long deliverMethodId, @RequestParam Long statusId) {
		Cart sp = this.cartService.getCart(currentUser.getUser());
		return this.orderService.createOrder(sp,paymentMethodId, deliverMethodId, statusId);
	}

}
