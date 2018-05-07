package com.globant.bootcamp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCartRepository extends ArrayList<ShoppingCart> {

	public List<ShoppingCart> getAll(){
		return this;
	}

	public ShoppingCart getById(Long id){
		ShoppingCart result = null;
		int index = this.getIndexOfById(id);
		if(index>=0){
			result = this.get(index);
		}
		return result;
	}

	private int getIndexOfById(Long id){
		int index = -1;
		List <ShoppingCart> list = this.getAll();
		for (int i = 0; i<list.size() && index == -1; i++){
			if(list.get(i).getId().equals(id)){
				index = i;
			}
		}
		return index;
	}
}
