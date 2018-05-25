package com.globant.bootcamp.controller;

import com.globant.bootcamp.payload.Product.CategoryResponse;
import com.globant.bootcamp.payload.Product.ProductResponse;
import com.globant.bootcamp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController @RequestMapping("/products") public class ProductController {

	@Autowired ProductService productService;


	@RequestMapping(method = RequestMethod.GET, produces = "application/json") public List<ProductResponse> getProducts() {
		return this.productService.getAllProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
	}

	@RequestMapping(value = "/{url}", method = RequestMethod.GET, produces = "application/json") public ProductResponse getProductByName(
			@PathVariable String url) {
		return new ProductResponse(this.productService.getProductByUrl(url));
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json") public List<CategoryResponse> getCategoriesNames() {
		return this.productService.getAllCategories().stream().map(CategoryResponse::new).collect(Collectors.toList());
	}

	@RequestMapping(value = "/categories/{url}", method = RequestMethod.GET, produces = "application/json") public List<ProductResponse> getProductsByCategory(
			@PathVariable String url) {
		return this.productService.getProductsByCategoryUrl(url).stream().map(ProductResponse::new).collect(Collectors.toList());
	}

}
