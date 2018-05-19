package com.globant.bootcamp.service;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.ShoppingCart;
import com.globant.bootcamp.persistence.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service public class ShoppingCartService {

	@Autowired ShoppingCartRepository shoppingCartRepository;

	/**
	 * Constructor
	 *
	 * @param repo instance of the Repository
	 */
	public ShoppingCartService(ShoppingCartRepository repo) {
		this.shoppingCartRepository = repo;
	}

	/**
	 * @param cartId
	 * @return Shopping Cart or null if not exists a matching id
	 */
	public ShoppingCart getShoppingCart(String cartId) {
		ShoppingCart sp = this.shoppingCartRepository.getById(cartId);
		if (sp == null) {
			sp = new ShoppingCart(cartId);
			this.shoppingCartRepository.save(sp);
		}

		return sp;
	}

	/**
	 * Add a product to the corresponding cart
	 *
	 * @param p      Product to be added
	 * @param cartId
	 */
	public void addProduct(Product p, String cartId) {
		ShoppingCart sp = this.getShoppingCart(cartId);
		if (sp != null) {
			sp.addProduct(p);
		}
		this.shoppingCartRepository.save(sp);
	}

	/**
	 * Remove a product of a Cart searched by id
	 *
	 * @param productId
	 * @param cartId
	 */
	public void removeProduct(Long productId, String cartId) {
		ShoppingCart sp = this.shoppingCartRepository.getById(cartId);
		if (sp != null) {
			sp.removeProductById(productId);
		}
		this.shoppingCartRepository.save(sp);
	}

	/**
	 * Remove all the products in a cart
	 *
	 * @param cartId
	 */
	public void clearCart(String cartId) {
		ShoppingCart sp = this.shoppingCartRepository.getById(cartId);
		if (sp != null) {
			sp.clear();
		}
		this.shoppingCartRepository.save(sp);
	}
}
