package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

public class OrderLine {
	@Getter @Setter @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private String id;

	@Getter @Setter @ManyToOne @JoinColumn(name = "product_id") private Product product;

	@Getter @Setter @Column(name = "qty") private int qty;
	@Setter @Column(name = "subtotal") private double subtotal;

	@ManyToOne @JoinColumn(name = "order_id") private Order order;

	public OrderLine(Product product, int qty) {
		this.product = product;
		this.qty = qty;
	}

	public double getSubtotal(){
		this.subtotal = product.getPrice() * qty;
		return this.subtotal;
	}
}
