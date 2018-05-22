package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.security.CurrentUser;
import com.globant.bootcamp.security.UserCredentials;
import com.globant.bootcamp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Cart getCart(@CurrentUser UserCredentials currentUser) {
		return cartService.getCart(currentUser.getUser());
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") public Cart addProductToCart(
			@RequestParam Long productId, @RequestParam int qty, @CurrentUser UserCredentials currentUser) {
		return this.cartService.addProduct(productId,qty,currentUser.getUser());
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json") public void emptyShoppingCart(
			@CurrentUser UserCredentials currentUser) {
		this.cartService.clearCart(currentUser.getUser());
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT, produces = "application/json") public Cart updateProductLine(
			@RequestParam Long productId, @RequestParam int qty, @CurrentUser UserCredentials currentUser) {
		return this.cartService.updateLineProduct(productId, qty, currentUser.getUser());
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, produces = "application/json") public Cart removeProduct(
			@RequestParam Long productId, @CurrentUser UserCredentials currentUser) {
		return this.cartService.removeProduct(productId, currentUser.getUser());
	}

}
