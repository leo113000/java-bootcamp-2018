package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	@Getter @Setter private String sessionId;
	private List<Product> productList;

	/**
	 * Constructor with params
	 *
	 * @param sessionId
	 */
	public ShoppingCart(String sessionId) {
		this.sessionId = sessionId;
		this.productList = new ArrayList<>();
	}

	/**
	 * This method add a Product's object into the cart
	 *
	 * @param product to be added
	 */
	public void addProduct(Product product) {
		this.productList.add(product);
	}

	/**
	 * Get a Product of the Cart By Id
	 *
	 * @param id
	 * @return a Product instance or null if there's not a product with the parameter's id
	 */
	public Product getProductById(Long id) {
		Product result = null;
		int index = this.getIndexOfById(id);
		if (index >= 0) {
			result = this.productList.get(index);
		}
		return result;
	}

	/**
	 * Remove a product of the cart by id
	 *
	 * @param id
	 */
	public void removeProductById(Long id) {
		int index = this.getIndexOfById(id);
		if (index >= 0) {
			this.productList.remove(index);
		}
	}

	/**
	 * Retrieves the product's list
	 *
	 * @return List with the products or a empty list
	 */
	public List<Product> getAllProducts() {
		return this.productList;
	}

	/**
	 * Empties the productList
	 */
	public void clear() {
		this.productList.clear();
	}

	/**
	 * @param id
	 * @return the index of that id in the array
	 */
	private int getIndexOfById(Long id) {
		int index = -1;

		for (int i = 0; i < this.productList.size() && index == -1; i++) {
			if (this.productList.get(i).getId().equals(id)) {
				index = i;
			}
		}
		return index;
	}
}
