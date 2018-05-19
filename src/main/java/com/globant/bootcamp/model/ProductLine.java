package com.globant.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductLine {
	private Product product;
	private int qty;

	public float getTotal() {
		return product.getPrice() * qty;
	}
}
