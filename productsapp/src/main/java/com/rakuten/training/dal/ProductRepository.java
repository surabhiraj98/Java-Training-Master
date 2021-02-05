package com.rakuten.training.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rakuten.training.domain.Product;

import net.bytebuddy.asm.Advice.Unused;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	public List<Product> findByPrice(float p);	
	public List<Product> findByPriceLessThan(float p);	
	public List<Product> findByName(String name);
	
	
//	Used to pass a query without using the naming convention defined by Spring
//	Query annotation with the sql query and using paramters to pass the values using Param annotations
	
	@Query("select p from Product as p where p.qoh=:q")
	public List<Product> myComplexQuery(@Param("q") int i);

}
