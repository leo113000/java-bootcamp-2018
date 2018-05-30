package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.ResourceNotFoundException;
import com.globant.bootcamp.model.Cart;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.model.User;
import com.globant.bootcamp.persistence.CartRepository;
import com.globant.bootcamp.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class CartService {

	@Autowired CartRepository cartRepository;
	@Autowired ProductRepository productRepository;

	/**
	 * Constructor
	 *
	 * @param cartRepo
	 * @param productRepo
	 */
	public CartService(CartRepository cartRepo, ProductRepository productRepo) {
		this.cartRepository = cartRepo;
		this.productRepository = productRepo;
	}

	/**
	 * @param user
	 * @return shopping Cart
	 */
	public Cart getCart(User user) {
		Cart cart = this.cartRepository.findByUserId(user.getId());

		return cart == null ? this.createCart(user) : cart;
	}

	/**
	 * Create a cart in the repository
	 *
	 * @param user
	 * @return The saved cart
	 */
	private Cart createCart(User user) {
		return this.cartRepository.save(new Cart(user));
	}

	/**
	 * Add a product to the cart
	 *
	 * @param productId
	 * @param qty
	 * @param user
	 * @return the cart with new product or null if the product not exists
	 */
	public Cart addProduct(Long productId, int qty, User user) throws ResourceNotFoundException {
		Cart cart = this.getCart(user);
		Product productOptional = productRepository.findById(productId).orElseThrow(ResourceNotFoundException::new);
		cart.addProduct(productOptional, qty);
		this.cartRepository.save(cart);

		return cart;
	}

	/**
	 * Update a product in the cart
	 *
	 * @param productId
	 * @param qty
	 * @param user
	 * @return
	 */
	public Cart updateLineProduct(Long productId, int qty, User user) throws ResourceNotFoundException {
		Cart cart = this.getCart(user);
		cart.removeProductById(productId);
		return addProduct(productId, qty, user);
	}

	/**
	 * Remove the CartLine with the product
	 *
	 * @param productId
	 * @param user
	 * @return the cart without the product
	 */
	public Cart removeProduct(Long productId, User user) {
		Cart cart = getCart(user);
		cart.removeProductById(productId);
		this.cartRepository.save(cart);
		return cart;
	}

	/**
	 * Remove all the products
	 *
	 * @param user
	 */
	public void clearCart(User user) {
		Cart cart = this.getCart(user);
		cart.clear();
		this.cartRepository.save(cart);
	}

}
