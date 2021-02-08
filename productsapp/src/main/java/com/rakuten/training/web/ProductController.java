package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService product_service;
	@Autowired
	ReviewService review_service;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return product_service.findAll();
	}
	
	@GetMapping("/products/{idPathVariable}")
	public ResponseEntity<Product> getProductById(@PathVariable("idPathVariable") int id) {
		Product product = product_service.findById(id);
		if (product!=null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity createNewProduct(@RequestBody Product toBeCreated){
		try {
			int id = product_service.createNewProduct(toBeCreated);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity<>(toBeCreated, headers, HttpStatus.CREATED);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/products/{idPathVariable}")
	public ResponseEntity deleteProduct(@PathVariable("idPathVariable") int id) {
		try {
			review_service.deleteByProduct_Id(id);
			product_service.removeExisting(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (IllegalStateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}
