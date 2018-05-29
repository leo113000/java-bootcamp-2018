package com.globant.bootcamp.controller;

import com.globant.bootcamp.exception.ResourceNotFoundException;
import com.globant.bootcamp.dto.ApiResponse;
import com.globant.bootcamp.dto.Shopping.CartResponse;
import com.globant.bootcamp.dto.Shopping.ProductCartRequest;
import com.globant.bootcamp.security.CurrentUser;
import com.globant.bootcamp.security.UserCredentials;
import com.globant.bootcamp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

	@PreAuthorize("hasAuthority('USER')") @RequestMapping(method = RequestMethod.GET, produces = "application/json") public CartResponse getCart(
			@CurrentUser UserCredentials currentUser) {
		return new CartResponse(cartService.getCart(currentUser.getUser()));
	}

	@PreAuthorize("hasAuthority('USER')") @RequestMapping(method = RequestMethod.POST, produces = "application/json") public ResponseEntity<?> addProductToCart(
			@Valid @ModelAttribute ProductCartRequest request, @CurrentUser UserCredentials currentUser) {
		try {
			CartResponse cartResponse = new CartResponse(
					cartService.addProduct(request.getProductId(), request.getQty(), currentUser.getUser()));
			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ApiResponse(false, "That product's id doesn't exists"), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('USER')") @RequestMapping(method = RequestMethod.DELETE, produces = "application/json") public ApiResponse emptyShoppingCart(
			@CurrentUser UserCredentials currentUser) {
		this.cartService.clearCart(currentUser.getUser());
		return new ApiResponse(true, "Cart clear");
	}

	@PreAuthorize("hasAuthority('USER')") @RequestMapping(method = RequestMethod.PUT, produces = "application/json") public ResponseEntity<?> updateProductLine(
			@Valid @ModelAttribute ProductCartRequest request, @CurrentUser UserCredentials currentUser) {
		try {
			CartResponse cartResponse = new CartResponse(
					this.cartService.updateLineProduct(request.getProductId(), request.getQty(), currentUser.getUser()));
			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ApiResponse(false, "That product's id doesn't exists"), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('USER')") @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, produces = "application/json") public CartResponse removeProduct(
			@Valid @ModelAttribute ProductCartRequest request, @CurrentUser UserCredentials currentUser) {
		return new CartResponse(this.cartService.removeProduct(request.getProductId(), currentUser.getUser()));
	}

}
