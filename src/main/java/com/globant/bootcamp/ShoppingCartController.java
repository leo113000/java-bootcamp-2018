package com.globant.bootcamp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartRepository repo;

	@RequestMapping( value = "/product", method = RequestMethod.POST, produces = "application/json")
	public ShoppingCart addProduct(Product p){
		return null;
	}
	@RequestMapping( value = "/product/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ShoppingCart removeProduct(Product p, Long id){
		return null;
	}
	@RequestMapping( method = RequestMethod.DELETE, produces = "application/json")
	public void emptyShoppingCart(){
	}
	@RequestMapping( value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart getShoppingCartById(Long id){
		return null;
	}
	@RequestMapping( method = RequestMethod.GET, produces = "application/json")
	public List getShoppingCarts(){
		return null;
	}
}
