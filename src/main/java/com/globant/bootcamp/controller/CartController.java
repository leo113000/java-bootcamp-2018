package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.payload.ApiResponse;
import com.globant.bootcamp.payload.Shopping.CartResponse;
import com.globant.bootcamp.payload.Shopping.ProductCartRequest;
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
	public CartResponse getCart(@CurrentUser UserCredentials currentUser) {
		return new CartResponse(cartService.getCart(currentUser.getUser()));
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json") public CartResponse addProductToCart(
			@ModelAttribute ProductCartRequest request, @CurrentUser UserCredentials currentUser) {
		return new CartResponse(cartService.addProduct(request.getProductId(),request.getQty(),currentUser.getUser()));
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json") public ApiResponse emptyShoppingCart(
			@CurrentUser UserCredentials currentUser) {
		this.cartService.clearCart(currentUser.getUser());
		return new ApiResponse(true,"Cart clear");
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT, produces = "application/json") public CartResponse updateProductLine(
			@ModelAttribute ProductCartRequest request, @CurrentUser UserCredentials currentUser) {
		return new CartResponse(this.cartService.updateLineProduct(request.getProductId(),request.getQty(), currentUser.getUser()));
	}

	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, produces = "application/json") public CartResponse removeProduct(
			@ModelAttribute ProductCartRequest request, @CurrentUser UserCredentials currentUser) {
		return new CartResponse(this.cartService.removeProduct(request.getProductId(), currentUser.getUser()));
	}

}
