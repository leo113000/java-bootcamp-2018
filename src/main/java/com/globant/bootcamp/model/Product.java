package com.globant.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor public class Product {
	private Long id;
	private String name;
	private float price;
	private int stock;
	private List<Category> categories;
}
