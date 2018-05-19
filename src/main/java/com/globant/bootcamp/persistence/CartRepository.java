package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository public class CartRepository extends ArrayList<Cart> implements iRepository<String, Cart> {

	public List<Cart> getAll() {
		return this;
	}

	public Cart getById(String sessionId) {
		return this.getAll().stream().filter(x -> {
			return x.getSessionId().equals(sessionId);
		}).findFirst().orElse(null);
	}

	public Cart save(Cart sp) {
		if(!contains(sp)){
			this.add(sp);
		}
		return sp;
	}

	@Override public void removeById(String sessionId) {
		Cart sp = this.getById(sessionId);
		this.remove(sp);
	}
}
