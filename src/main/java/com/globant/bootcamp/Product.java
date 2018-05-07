package com.globant.bootcamp;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Product {
	private Long id;
	private String name;

	/**
	 * Auto-generated equals
	 * @param o
	 * @return true if the objects are equal or false if not
	 */
	@Override public boolean equals(Object o) {
		boolean result = false;
		if(o instanceof  ShoppingCart){
			if(this.getId().equals(((ShoppingCart) o).getId())){
				result = true;
			}
		}
		return result;
	}
}
