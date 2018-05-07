package com.globant.bootcamp;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartService(ShoppingCartRepository repo) {
		this.shoppingCartRepository = repo;
	}

	public List<Product> getProducts(Long cartId) {
		List<Product> allProducts = new ArrayList<>();
		ShoppingCart sp =this.shoppingCartRepository.getById(cartId);
		if(sp != null){
			allProducts = sp.getAllProducts();
		}
		return allProducts;
	}

	public void addProduct(Product p, Long cartId) {
		ShoppingCart sp = this.shoppingCartRepository.getById(cartId);
		if (sp != null) {
			sp.addProduct(p);
		}
	}

	public void removeProduct(Long productId, Long cartId) {
		ShoppingCart sp = this.shoppingCartRepository.getById(cartId);
		if (sp != null) {
			sp.removeProductById(productId);
		}
	}

	public void clearCart(Long cartId) {
		ShoppingCart sp = this.shoppingCartRepository.getById(cartId);
		if(sp != null){
			sp.clear();
		}
	}
}
