package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "payment_methods", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }) }) public class PaymentMethod {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@Column(name = "name") private String name;
}
