package com.globant.bootcamp.payload.shopping;

import com.globant.bootcamp.model.Order;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter public class OrderResponse {
	private String username;
	private Date date;
	private double total;
	private String paymentMethod;
	private String deliverMethod;
	private String status;
	private List<LineResponse> lineResponseList;

	public OrderResponse(Order orderModel) {
		this.username = orderModel.getUser().getUsername();
		this.date = orderModel.getDate();
		this.total = orderModel.getTotal();
		this.paymentMethod = orderModel.getPaymentMethod().getName();
		this.deliverMethod = orderModel.getDeliverMethod().getName();
		this.status = orderModel.getStatus().getName();
		this.lineResponseList = orderModel.getOrderLines().stream().map(LineResponse::new).collect(Collectors.toList());
	}
}
