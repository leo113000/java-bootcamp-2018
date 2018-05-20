package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter @Entity @Table(name = "carts") public class Cart {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
	@OneToOne @JoinColumn(name = "user_id") private User user;
	@OneToMany(mappedBy = "carts", cascade = CascadeType.ALL, fetch = FetchType.EAGER) private Map<Long, ProductLine> productList;
	private double totalPrice;

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
	 * @param p   Product
	 * @param qty quantity
	 */
	public void addProduct(Product p, int qty) {
		this.productList.put(p.getId(), new ProductLine(p, qty));
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
	 * @return total price of the cart
	 */
	public double getTotal() {
		double total = (float) 0;
		for (ProductLine pl : this.productList.values()) {
			total += pl.getTotal();
		}
		this.totalPrice = total;
		return total;
	}
}
