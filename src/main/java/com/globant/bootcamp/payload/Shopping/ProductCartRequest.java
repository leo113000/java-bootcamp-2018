package com.globant.bootcamp.payload.Shopping;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter public class ProductCartRequest {
	@NotNull private Long productId;
	private int qty;
}
