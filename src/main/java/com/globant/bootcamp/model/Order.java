package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter @Entity @Table(name = "orders") public class Order {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
	@OneToOne @JoinColumn(name = "cart_id") private Cart cart;
	@Column(name = "date") private Date date;
	@Column(name = "total") private double total;
	@OneToMany(mappedBy = "deliver_methods") private DeliveryMethod deliveryMethod;
	@OneToMany(mappedBy = "payment_methods") private PaymentMethod paymentMethod;
	@OneToMany(mappedBy = "statuses") private Status status;
}
