package com.rakuten.training.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@RestController
public class TestController {

	List<Product> products = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		Review r1 = new Review("Pratik", "Good quality", 5);
		Review r2 = new Review("Pranay", "average quality", 3);
		Product p1 = new Product("Macbook", 50000.00f, 20, r1);
		p1.addReview(r2);
		products.add(p1);
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return products;
	}

	@PostMapping("/products")
	public Product addGreeting(@RequestBody Product toBeAdded) {
		products.add(toBeAdded);
		return toBeAdded;
	}

}
