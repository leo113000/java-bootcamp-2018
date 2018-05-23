package com.globant.bootcamp.payload.Shopping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCartRequest {
	private Long productId;
	private int qty;
}
