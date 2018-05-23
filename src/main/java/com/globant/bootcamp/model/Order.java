package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "orders") public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@Getter @Setter @OneToOne @JoinColumn(name = "user_id") private User user;
	@Column(name = "date") private Date date;
	@Column(name = "total") private double total;
	@ManyToOne @JoinColumn(name = "deliver_method_id") private DeliveryMethod deliveryMethod;
	@ManyToOne @JoinColumn(name = "payment_method_id") private PaymentMethod paymentMethod;
	@ManyToOne @JoinColumn(name = "status_id") private Status status;

	@Getter @Setter @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER) private List<OrderLine> orderLines;

	public Order(User user, Date date, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, Status status) {
		this.user = user;
		this.date = date;
		this.deliveryMethod = deliveryMethod;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}
}
