package com.globant.bootcamp.payload.Shopping;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;


@Getter
@Setter
public class OrderRequest {
	private Long paymentMethodId;
	private Long deliverMethodId;
}
