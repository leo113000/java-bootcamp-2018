package com.globant.bootcamp.controller;

import com.globant.bootcamp.exception.BadRequestException;
import com.globant.bootcamp.exception.EmptyCartException;
import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.model.Order;
import com.globant.bootcamp.payload.ApiResponse;
import com.globant.bootcamp.payload.Shopping.OrderRequest;
import com.globant.bootcamp.payload.Shopping.OrderResponse;
import com.globant.bootcamp.security.CurrentUser;
import com.globant.bootcamp.security.UserCredentials;
import com.globant.bootcamp.service.CartService;
import com.globant.bootcamp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController @RequestMapping("/orders") public class OrderController {

	@Autowired private CartService cartService;
	@Autowired private OrderService orderService;

	public OrderController(CartService cartService, OrderService orderService) {
		this.cartService = cartService;
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json") public List<OrderResponse> getOrders(
			@CurrentUser UserCredentials userCrendentials) {
		return this.orderService.getNotFinishedUserOrders(userCrendentials.getUser()).stream().map(OrderResponse::new)
				.collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json") public ResponseEntity<?> createOrder(
			@CurrentUser UserCredentials currentUser, @Valid @ModelAttribute OrderRequest request) {
		Cart sp = this.cartService.getCart(currentUser.getUser());
		try {
			Order newOrder = this.orderService
					.createOrder(currentUser.getUser(), sp, request.getPaymentMethodId(), request.getDeliverMethodId());
			this.cartService.clearCart(currentUser.getUser());
			return new ResponseEntity<>(new OrderResponse(newOrder), HttpStatus.OK);
		} catch (BadRequestException e) {
			return new ResponseEntity<>(new ApiResponse(false, "Wrong parameters"), HttpStatus.BAD_REQUEST);
		} catch (EmptyCartException e) {
			return new ResponseEntity<>(new ApiResponse(false, "The cart is empty"), HttpStatus.BAD_REQUEST);
		}
	}

}
