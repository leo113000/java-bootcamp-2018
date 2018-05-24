package com.globant.bootcamp.model;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor @Entity @Table(name = "carts") public class Cart {
	@Getter @Setter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
	@Getter @Setter @OneToOne @JoinColumn(name = "user_id") private User user;

	@Getter @Setter @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) private List<ProductLine> productList;

	@Setter
	@Column(name = "total")
	private double total;

	public double getTotal() {
		AtomicDouble atomicTotal = new AtomicDouble();
		this.productList.forEach(x -> atomicTotal.addAndGet(x.getSubtotal()));
		return atomicTotal.get();
	}


	/**
	 * Constructor with params
	 *
	 * @param u User owner
	 */
	public Cart(User u) {
		this.user = u;
		this.productList = new ArrayList<>();
	}

	/**
	 * @param p   Product
	 * @param qty quantity
	 */
	public void addProduct(Product p, int qty) {
		Optional<ProductLine> opProduct = productList.stream().filter(x -> x.getProduct().getId().equals(p.getId())).findFirst();
		if(opProduct.isPresent()){
			opProduct.get().setQty(opProduct.get().getQty() + qty);
		}else{
			this.productList.add(new ProductLine(p,qty,this));
		}

	}

	/**
	 * @param id of the Product
	 * @return void
	 */
	public void removeProductById(Long id) {
		this.productList.removeIf(x -> {
			return x.getProduct().getId().equals(id);
		});
	}

	/**
	 * Empties the cart
	 */
	public void clear() {
		this.productList.clear();
	}

}
