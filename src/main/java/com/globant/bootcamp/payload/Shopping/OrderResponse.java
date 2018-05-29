package com.globant.bootcamp.payload.Shopping;

import com.globant.bootcamp.model.Order;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter public class OrderResponse {
	private String username;
	private Date date;
	private double total;
	private String payment_method;
	private String deliver_method;
	private String status;
	private List<LineResponse> lineResponseList;

	public OrderResponse(Order orderModel) {
		this.username = orderModel.getUser().getUsername();
		this.date = orderModel.getDate();
		this.total = orderModel.getTotal();
		this.payment_method = orderModel.getPaymentMethod().getName();
		this.deliver_method = orderModel.getDeliverMethod().getName();
		this.status = orderModel.getStatus().getName();
		this.lineResponseList = orderModel.getOrderLines().stream().map(LineResponse::new).collect(Collectors.toList());
	}
}
