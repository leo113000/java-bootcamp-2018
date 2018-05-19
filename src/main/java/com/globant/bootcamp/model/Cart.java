package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	@Getter @Setter private Long id;
	@Getter @Setter private User user;
	private Map<Long,ProductLine> productList;

	/**
	 * Constructor with params
	 *
	 * @param u User owner
	 */
	public Cart(User u) {
		this.user = u;
		this.productList = new HashMap<>();
	}

	/**
	 *
	 * @param p Product
	 * @param qty quantity
	 */
	public void addProduct(Product p, int qty) {
		this.productList.put(p.getId(),new ProductLine(p,qty));
	}

	/**
	 * Get a Product of the Cart By Id
	 *
	 * @param id
	 * @return a ProductLine instance or null if there's not a product with the parameter's id
	 */
	public ProductLine getProductById(Long id) {
		return this.productList.get(id);
	}

	/**
	 *
	 * @param id
	 * @return The removed obj or null
	 */
	public ProductLine removeProductById(Long id) {
		return this.productList.remove(id);
	}

	/**
	 * Retrieves the product lines list
	 *
	 * @return List with the products or a empty list
	 */
	public List<ProductLine> getAllProducts() {
		return new ArrayList<>(this.productList.values());
	}

	/**
	 * Empties the cart
	 */
	public void clear() {
		this.productList.clear();
	}

	/**
	 *
	 * @return total price of the cart
	 */
	public Float getTotal(){
		Float total = (float) 0;
		for(ProductLine pl : this.productList.values()){
			total += pl.getTotal();
		}
		return total;
	}
}
