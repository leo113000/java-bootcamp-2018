package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @Entity @Table(name = "product_lines") public class ProductLine {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private String id;

	@ManyToOne @JoinColumn(name = "product_id") private Product product;

	@Column(name = "qty") private int qty;
	@Column(name = "subtotal") private int subtotal;

	@ManyToOne @JoinColumn(name = "cart_id") private Cart cart;

	public ProductLine(Product product, int qty) {
		this.product = product;
		this.qty = qty;
	}
}
