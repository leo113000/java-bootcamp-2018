package com.globant.bootcamp;

import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ShoppingCartRepository extends ArrayDeque<ShoppingCart> {

	public List<ShoppingCart> getAll(){
		return new ArrayList<>(this);
	}

}
