package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import com.globant.bootcamp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/products") public class ProductController {

	@Autowired ProductService productService;


	@RequestMapping(method = RequestMethod.GET, produces = "application/json") public List<Product> getProducts() {
		return this.productService.getAllProducts();
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json") public Product getProductByName(
			@PathVariable String name) {
		return this.productService.getProductByName(name);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json") public List<Category> getCategoriesNames() {
		return this.productService.getAllCategories();
	}

	@RequestMapping(value = "/categories/{name}", method = RequestMethod.GET, produces = "application/json") public List<Product> getProductsByCategory(
			@PathVariable String name) {
		return this.productService.getProductsByCategory(name);
	}

}
