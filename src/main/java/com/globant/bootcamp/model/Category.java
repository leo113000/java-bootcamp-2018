package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "categories", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "url" }) }) public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@Column(name = "name") private String name;
	@Column(name = "url") private String url;
	@ManyToMany(mappedBy = "categories") private List<Product> productList;
}
