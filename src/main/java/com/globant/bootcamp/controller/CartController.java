package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController @RequestMapping("/cart") public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * Constructor
	 *
	 * @param cartService
	 */
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Cart getCart(HttpServletResponse response) {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json") public Cart addProductToCart() {
		return null;
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json") public void emptyShoppingCart() {
		//
	}

	@RequestMapping(value = "/{productLineId}", method = RequestMethod.PUT, produces = "application/json") public Cart updateProductLine(
			@PathVariable Long ProductId) {
		return null;
	}

	@RequestMapping(value = "/{productLineId}", method = RequestMethod.DELETE, produces = "application/json") public Cart removeProduct(
			@PathVariable Long ProductId) {
		return null;
	}

}
