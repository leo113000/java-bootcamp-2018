package com.globant.bootcamp.controller;


import com.globant.bootcamp.model.Category;
import com.globant.bootcamp.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@RequestMapping( method= RequestMethod.GET, produces= "application/json" )
	public List<Product> getProducts(){
		return null;
	}
	@RequestMapping( value = "/{name}" ,method= RequestMethod.GET, produces= "application/json" )
	public Product getProductByName(@PathVariable String name){
		return null;
	}
	@RequestMapping( value = "/categories" ,method= RequestMethod.GET, produces= "application/json" )
	public List<String> getCategoriesNames(){
		return null;
	}
	@RequestMapping( value = "/categories/{name}" ,method= RequestMethod.GET, produces= "application/json" )
	public List<Product> getProductsByCategory(@PathVariable String name){
		return null;
	}

}
