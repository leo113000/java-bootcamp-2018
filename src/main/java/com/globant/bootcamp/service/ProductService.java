package com.globant.bootcamp.service;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.persistence.CategoryRepository;
import com.globant.bootcamp.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<Product> getAllProducts(){
		return this.productRepository.findAll();
	}

	public Product getProductByName(String url){
		return this.productRepository.findByUrl(url);
	}

	public List<Product> getProductsByCategory(String url){
		return this.productRepository.findByCategories(this.categoryRepository.findByUrl(url));
	}

	public List<Category> getAllCategories(){
		return this.categoryRepository.findAll();
	}

}
