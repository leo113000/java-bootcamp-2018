package com.globant.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter @AllArgsConstructor @Entity @Table(name = "products") public class Product {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
	@NotEmpty(message = "Product Name is mandatory") @Column(name = "name") private String name;
	@NotNull(message = "Please provide some price") @Column(name = "price") private double price;
	@Column(name = "stock") private int stock;
	@ManyToMany(cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }) @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id")) private List<Category> categories;
}
