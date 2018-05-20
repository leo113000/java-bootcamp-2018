package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "payment_methods") public class PaymentMethod {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
	@Column(name = "name") private String name;
}
