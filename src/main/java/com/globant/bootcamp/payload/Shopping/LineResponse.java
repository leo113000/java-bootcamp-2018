package com.globant.bootcamp.payload.Shopping;

import com.globant.bootcamp.model.OrderLine;
import com.globant.bootcamp.model.ProductLine;
import lombok.Getter;

@Getter
public class LineResponse {
	private Long productId;
	private String productName;
	private int quantity;
	private double value;

	public LineResponse(ProductLine productLineModel) {
		this.productId = productLineModel.getProduct().getId();
		this.productName = productLineModel.getProduct().getName();
		this.quantity = productLineModel.getQty();
		this.value = productLineModel.getSubtotal();
	}

	public LineResponse(OrderLine orderLineModel) {
		this.productId = orderLineModel.getProduct().getId();
		this.productName = orderLineModel.getProduct().getName();
		this.quantity = orderLineModel.getQty();
		this.value = orderLineModel.getSubtotal();
	}
}
