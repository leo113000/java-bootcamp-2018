package com.globant.bootcamp.service;

import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service public class CartService {

	@Autowired CartRepository cartRepository;

	/**
	 * Constructor
	 *
	 * @param repo instance of the Repository
	 */
	public CartService(CartRepository repo) {
		this.cartRepository = repo;
	}

	/**
	 * @param cartId
	 * @return Shopping Cart or null if not exists a matching id
	 */
	public Cart getShoppingCart(String cartId) {
		Cart sp = this.cartRepository.getById(cartId);
		if (sp == null) {
			sp = new Cart(cartId);
			this.cartRepository.save(sp);
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
		Cart sp = this.getShoppingCart(cartId);
		if (sp != null) {
			sp.addProduct(p);
		}
		this.cartRepository.save(sp);
	}

	/**
	 * Remove a product of a Cart searched by id
	 *
	 * @param productId
	 * @param cartId
	 */
	public void removeProduct(Long productId, String cartId) {
		Cart sp = this.cartRepository.getById(cartId);
		if (sp != null) {
			sp.removeProductById(productId);
		}
		this.cartRepository.save(sp);
	}

	/**
	 * Remove all the products in a cart
	 *
	 * @param cartId
	 */
	public void clearCart(String cartId) {
		Cart sp = this.cartRepository.getById(cartId);
		if (sp != null) {
			sp.clear();
		}
		this.cartRepository.save(sp);
	}
}
