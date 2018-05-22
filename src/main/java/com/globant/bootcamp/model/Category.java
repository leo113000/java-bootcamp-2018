package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "categories", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }) }) public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@Column(name = "name") private String name;
	@ManyToMany(mappedBy = "categories") private List<Product> productList;
}
