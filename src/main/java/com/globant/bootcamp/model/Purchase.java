package com.globant.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Purchase {
	private Long id;
	private Cart cart;
	private Date date;
	private float total;
	private DeliveryMethod deliveryMethod;
	private PaymentMethod paymentMethod;
	private Status status;
}
