package com.globant.bootcamp.dto.Shopping;

import com.globant.bootcamp.model.CartLine;
import com.globant.bootcamp.model.OrderLine;
import lombok.Getter;

@Getter public class LineResponse {
	private Long productId;
	private String productName;
	private int quantity;
	private double value;

	public LineResponse(CartLine cartLineModel) {
		this.productId = cartLineModel.getProduct().getId();
		this.productName = cartLineModel.getProduct().getName();
		this.quantity = cartLineModel.getQty();
		this.value = cartLineModel.getSubtotal();
	}

	public LineResponse(OrderLine orderLineModel) {
		this.productId = orderLineModel.getProduct().getId();
		this.productName = orderLineModel.getProduct().getName();
		this.quantity = orderLineModel.getQty();
		this.value = orderLineModel.getSubtotal();
	}
}
