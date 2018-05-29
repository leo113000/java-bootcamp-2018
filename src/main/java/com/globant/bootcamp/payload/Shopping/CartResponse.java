package com.globant.bootcamp.payload.Shopping;

import com.globant.bootcamp.model.Cart;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter public class CartResponse {

	private String username;
	private double total;
	private List<LineResponse> lineResponseList;

	public CartResponse(Cart cartModel) {
		this.username = cartModel.getUser().getUsername();
		this.total = cartModel.getTotal();
		this.lineResponseList = cartModel.getProductList().stream().map(LineResponse::new).collect(Collectors.toList());
	}
}
