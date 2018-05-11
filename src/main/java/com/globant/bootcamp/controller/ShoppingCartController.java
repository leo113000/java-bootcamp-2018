package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController @RequestMapping public class ShoppingCartController {

	@Autowired private ShoppingCartService shoppingCartService;

	/**
	 * Constructor
	 *
	 * @param shoppingCartService
	 */
	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET, produces = "application/json") public ShoppingCart getShoppingCartBySessionId() {
		return shoppingCartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/cart", method = RequestMethod.DELETE, produces = "application/json") public void emptyShoppingCart() {
		this.shoppingCartService.clearCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/products/", method = RequestMethod.POST, produces = "application/json") public ShoppingCart addProduct(
			Product p) {
		this.shoppingCartService.addProduct(p, RequestContextHolder.currentRequestAttributes().getSessionId());
		return this.shoppingCartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/products/", method = RequestMethod.DELETE, produces = "application/json") public ShoppingCart removeProduct(
			Product p) {
		this.shoppingCartService.removeProduct(p.getId(), RequestContextHolder.currentRequestAttributes().getSessionId());
		return this.shoppingCartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}
}
