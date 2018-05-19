package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository public class ShoppingCartRepository extends ArrayList<ShoppingCart> implements iRepository<String, ShoppingCart> {

	public List<ShoppingCart> getAll() {
		return this;
	}

	public ShoppingCart getById(String sessionId) {
		return this.getAll().stream().filter(x -> {
			return x.getSessionId().equals(sessionId);
		}).findFirst().orElse(null);
	}

	public ShoppingCart save(ShoppingCart sp) {
		if(!contains(sp)){
			this.add(sp);
		}
		return sp;
	}

	@Override public void removeById(String sessionId) {
		ShoppingCart sp = this.getById(sessionId);
		this.remove(sp);
	}
}
