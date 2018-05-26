package com.globant.bootcamp.model;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @Getter @Setter @Entity @Table(name = "orders") public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@Getter @Setter @OneToOne @JoinColumn(name = "user_id") private User user;
	@Getter @Setter @Column(name = "date") private Date date;
	@Setter @Column(name = "total") private double total;
	@ManyToOne @JoinColumn(name = "deliverMethodId") private DeliverMethod deliverMethod;
	@ManyToOne @JoinColumn(name = "paymentMethodId") private PaymentMethod paymentMethod;
	@ManyToOne @JoinColumn(name = "status_id") private Status status;

	@Getter @Setter @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER) private List<OrderLine> orderLines;

	public Order(User user, Date date, DeliverMethod deliverMethod, PaymentMethod paymentMethod, Status status) {
		this.user = user;
		this.date = date;
		this.deliverMethod = deliverMethod;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.orderLines = new ArrayList<>();
	}

	public void addCartLines(Cart cart) {
		cart.getProductList().forEach( x -> this.orderLines.add(new OrderLine(x.getProduct(),x.getQty(),this)) );
	}

	public double getTotal() {
		AtomicDouble atomicTotal = new AtomicDouble();
		this.orderLines.forEach(x -> atomicTotal.addAndGet(x.getSubtotal()));
		this.setTotal(atomicTotal.get());
		return this.total;
	}
}
