package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor @Entity @Table(name = "order_lines")public class OrderLine {
	@Getter @Setter @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;

	@Getter @Setter @ManyToOne @JoinColumn(name = "product_id") private Product product;

	@Getter @Setter @Column(name = "qty") private int qty;
	@Setter @Column(name = "subtotal") private double subtotal;

	@Getter @Setter @ManyToOne @JoinColumn(name = "order_id") private Order order;

	public OrderLine(Product product, int qty,Order order) {
		this.product = product;
		this.qty = qty;
		this.order = order;
	}

	public double getSubtotal(){
		this.subtotal = product.getPrice() * qty;
		return this.subtotal;
	}
}
