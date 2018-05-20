package com.globant.bootcamp.controller;


import com.globant.bootcamp.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/cart") public class CartController {

	@Autowired
	//private CartService cartService;

	/**
	 * Constructor
	 *
	 * @param cartService
	 */
	//public CartController(CartService cartService) {
	//	this.cartService = cartService;
	//}

	@RequestMapping( method = RequestMethod.GET, produces = "application/json") public Cart getShoppingCart() {
		return null;
	}

	@RequestMapping( method = RequestMethod.POST, produces = "application/json") public Cart addProductToCart(){
		return null;
	}

	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json") public void emptyShoppingCart() {
		//
	}

	@RequestMapping ( value = "/{productLineId}", method = RequestMethod.PUT, produces = "application/json") public Cart updateProductLine(@PathVariable Long ProductLineId){
		return null;
	}

	@RequestMapping ( value = "/{productLineId}", method = RequestMethod.DELETE, produces = "application/json") public Cart deleteProductLine(@PathVariable Long ProductLineId){
		return null;
	}

}
