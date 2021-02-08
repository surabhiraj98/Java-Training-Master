package com.rakuten.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO reviewDAO;

	@Autowired
	ProductDAO productDAO;

	@Override
	public Review addReviewToProduct(Review r, int productId) {
		Product product = productDAO.findById(productId);
		r.setProduct(product);
		return reviewDAO.save(r);
	}

	@Override
	public Review findById(int id) {
		return reviewDAO.findById(id);
	}

	@Override
	public List<Review> findByProduct_Id(int pid) {
		return reviewDAO.findByProduct_Id(pid);
	}
	
	@Override
	public void deleteByProduct_Id(int pid) {
		if(productDAO.findById(pid)!=null) {
			reviewDAO.deleteByProduct_Id(pid);
		}
		else {
			throw new IllegalArgumentException("Product Doesn't exist");
		}
	}
	
	@Override
	public void deleteByReview_Id(int rid) {
		if(reviewDAO.findById(rid)!=null) {
			reviewDAO.deleteByReview_Id(rid);
		}
		else {
			throw new IllegalArgumentException("Product Doesn't exist");
		}
	}
}
