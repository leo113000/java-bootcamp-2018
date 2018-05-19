package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController @RequestMapping("/cart") public class ShoppingCartController {

	@Autowired private ShoppingCartService shoppingCartService;

	/**
	 * Constructor
	 *
	 * @param shoppingCartService
	 */
	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@RequestMapping( method = RequestMethod.GET, produces = "application/json") public ShoppingCart getShoppingCartBySessionId() {
		return shoppingCartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json") public void emptyShoppingCart() {
		this.shoppingCartService.clearCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json") public ShoppingCart addProduct(
			@RequestParam Long id, @RequestParam String name) {
		Product p = new Product(id,name);
		System.out.println("Controller:" + p.getName());
		this.shoppingCartService.addProduct(p, RequestContextHolder.currentRequestAttributes().getSessionId());
		return this.shoppingCartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/products", method = RequestMethod.DELETE, produces = "application/json") public ShoppingCart removeProduct(
			@RequestParam Long id) {
		this.shoppingCartService.removeProduct(id, RequestContextHolder.currentRequestAttributes().getSessionId());
		return this.shoppingCartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}
}
