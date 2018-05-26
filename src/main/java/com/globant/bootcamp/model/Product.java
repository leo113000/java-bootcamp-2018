package com.globant.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor @Getter @Setter @AllArgsConstructor @Entity @Table(name = "products") public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@NotEmpty @Column(name = "name") private String name;
	@NotEmpty @Column(name = "url") private String url;
	@NotNull @Column(name = "price") private double price;
	@ManyToMany(cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }) @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id")) private List<Category> categories;
}
