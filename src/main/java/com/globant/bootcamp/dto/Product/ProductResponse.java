package com.globant.bootcamp.dto.Product;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter public class ProductResponse {
	private Long productId;
	private String url;
	private String name;
	private double price;
	private List<String> categories;

	public ProductResponse(Product productModel) {
		this.productId = productModel.getId();
		this.url = productModel.getUrl();
		this.name = productModel.getName();
		this.price = productModel.getPrice();
		categories = productModel.getCategories() != null ?
				productModel.getCategories().stream().map(Category::getName).collect(Collectors.toList()) :
				new ArrayList<>();
	}
}
