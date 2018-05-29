package com.globant.bootcamp.service;

import com.globant.bootcamp.exception.ResourceNotFoundException;
import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.persistence.CategoryRepository;
import com.globant.bootcamp.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class ProductService {

	@Autowired private ProductRepository productRepository;
	@Autowired private CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	public Product getProductByUrl(String url) throws ResourceNotFoundException {
		return this.productRepository.findByUrl(url).orElseThrow(ResourceNotFoundException::new);
	}

	public List<Product> getProductsByCategoryUrl(String url) throws ResourceNotFoundException {
		return this.productRepository.findByCategories(this.categoryRepository.findByUrl(url).orElseThrow(ResourceNotFoundException::new));
	}

	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}

}
