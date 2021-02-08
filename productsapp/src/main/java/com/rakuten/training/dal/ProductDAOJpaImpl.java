package com.rakuten.training.dal;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ReviewService;
import com.rakuten.training.service.ReviewServiceImpl;

@Primary
@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO {
	
	@Autowired
	EntityManager em;

	@Override
	public Product save(Product toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Product findById(int id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		Query query = em.createQuery("select p from Product as p");
		return query.getResultList();
		
	}

	@Override
	public void deleteById(int id) {
//		Query query = em.createQuery("DELETE from Review r where r.product.id=:pid");
//		query.setParameter("pid", id);
//		int numReviewsDeleted = query.executeUpdate();
//		System.out.println("<<<<< Deleted " +numReviewsDeleted + "Reviews before deleting product with id = " +id+">>>>>>");
		Product p = em.find(Product.class, id);
		em.remove(p);
	}

}
