package com.globant.bootcamp;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
	@Getter
	@Setter
	private Long id;
	private List<Product> productList;

	public ShoppingCart(Long id) {
		this.id = id;
		this.productList = new ArrayList<>();
	}

	public void addProduct(Product product){
		this.productList.add(product);
	}

	public Product getProductById(Long id){
		Product result = null;
		int index = this.getIndexOfById(id);
		if(index>=0){
			result = this.productList.get(index);
		}
		return result;
	}

	public void removeProductById(Long id){
		int index = this.getIndexOfById(id);
		if(index>=0){
			this.productList.remove(index);
		}
	}

	public List <Product> getAllProducts(){
		return this.productList;
	}

	public void clear(){
		this.productList.clear();
	}

	private int getIndexOfById(Long id){
		int index = -1;

		for (int i = 0; i<this.productList.size() && index == -1; i++){
			if(this.productList.get(i).getId().equals(id)){
				index = i;
			}
		}
		return index;
	}
}
