package com.globant.bootcamp.payload.Shopping;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter public class OrderRequest {
	@NotNull private Long paymentMethodId;
	@NotNull private Long deliverMethodId;
}
