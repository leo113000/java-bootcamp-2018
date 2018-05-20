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
	@ManyToOne @JoinColumn(name = "deliver_method_id") private DeliveryMethod deliveryMethod;
	@ManyToOne @JoinColumn(name = "payment_method_id") private PaymentMethod paymentMethod;
	@ManyToOne @JoinColumn(name = "status_id") private Status status;
}
