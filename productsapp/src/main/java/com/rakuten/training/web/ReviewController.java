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
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products/{idPathVariable}/reviews")
	public ResponseEntity getAllReviews(@PathVariable("idPathVariable") int id) {
		Product p = productService.findById(id);
		if(p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Review> reviews = reviewService.findByProduct_Id(id);
		return new ResponseEntity(reviews, HttpStatus.OK);
	}
	
	@PostMapping("/products/{idPathVariable}/reviews")
	public ResponseEntity addReview(@RequestBody Review review, @PathVariable("idPathVariable") int id) {
		Product p = productService.findById(id);
		if(p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Review added = reviewService.addReviewToProduct(review, id);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/products/"+id+"/reviews/"+added.getId()));
		return new ResponseEntity(added, headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/products/{idPathVariable}/reviews")
	public ResponseEntity deleteReview(@PathVariable("idPathVariable") int pid) {
		try{
			reviewService.deleteByProduct_Id(pid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/products/{idPathVariable}/reviews/{rIdPathVariable}")
	public ResponseEntity deleteReviewBy_Id(@PathVariable("rIdPathVariable") int rid) {
		try{
			reviewService.deleteByReview_Id(rid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
}
