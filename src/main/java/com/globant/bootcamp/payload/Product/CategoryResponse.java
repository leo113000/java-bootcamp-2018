package com.globant.bootcamp.payload.Product;

import com.globant.bootcamp.model.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {
	private String name;
	private String url;

	public CategoryResponse(Category categoryModel) {
		this.name = categoryModel.getName();
		this.url = categoryModel.getUrl();
	}
}
