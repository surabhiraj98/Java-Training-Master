package com.rakuten.training.domain;

import java.util.ArrayList;
import java.util.List;


public class Product {
	
	String name;
	float price;
	int qoh;
	
	List<Review> reviews = new ArrayList<>();
	
	public Product() {
		
	}

	public Product(String name, float price, int qoh, Review review) {
		super();
		this.name = name;
		this.price = price;
		this.qoh = qoh;
		this.reviews.add(review);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQoh() {
		return qoh;
	}

	public void setQoh(int qoh) {
		this.qoh = qoh;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
}
