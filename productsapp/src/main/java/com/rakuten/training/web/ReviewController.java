package com.rakuten.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService service;
	
	@GetMapping("/products/{idPathVariable}/reviews")
	public List<Review> getAllReviews(@PathVariable("idPathVariable") int id) {
		return service.findByProduct_Id(id);
	}
	
	@PostMapping("/products/{idPathVariable}/reviews")
	public ResponseEntity addReview(@RequestBody Review review, @PathVariable("idPathVariable") int id) {
		service.addReviewToProduct(review, id);
		return new ResponseEntity<>(review, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/products/{idPathVariable}/reviews")
	public ResponseEntity deleteReview(@PathVariable("idPathVariable") int pid) {
		try{
			service.deleteByProduct_Id(pid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/products/{idPathVariable}/reviews/{rIdPathVariable}")
	public ResponseEntity deleteReviewBy_Id(@PathVariable("rIdPathVariable") int rid) {
		try{
			service.deleteByReview_Id(rid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
}
