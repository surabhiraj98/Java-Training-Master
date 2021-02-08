package com.rakuten.training.domain;

public class Review {
	
	String author;
	String desc;
	int rating;
	
	public Review() {
		
	}

	public Review(String author, String desc, int rating) {
		super();
		this.author = author;
		this.desc = desc;
		this.rating = rating;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
