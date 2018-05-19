package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController @RequestMapping("/cart") public class CartController {

	@Autowired private CartService cartService;

	/**
	 * Constructor
	 *
	 * @param cartService
	 */
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping( method = RequestMethod.GET, produces = "application/json") public Cart getShoppingCartBySessionId() {
		return cartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json") public void emptyShoppingCart() {
		this.cartService.clearCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json") public Cart addProduct(
			@RequestParam Long id, @RequestParam String name) {
		Product p = new Product(id,name);
		System.out.println("Controller:" + p.getName());
		this.cartService.addProduct(p, RequestContextHolder.currentRequestAttributes().getSessionId());
		return this.cartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	@RequestMapping(value = "/products", method = RequestMethod.DELETE, produces = "application/json") public Cart removeProduct(
			@RequestParam Long id) {
		this.cartService.removeProduct(id, RequestContextHolder.currentRequestAttributes().getSessionId());
		return this.cartService.getShoppingCart(RequestContextHolder.currentRequestAttributes().getSessionId());
	}
}
