package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.User;

import net.bytebuddy.asm.Advice.Return;

@Primary
@Repository
@Transactional
public class UserDAOJpaImpl implements UserDAO{
	
	@Autowired
	EntityManager em;

	@Override
	public User save(User toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public User findById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		Query query = em.createQuery("select u from User as u");
		return query.getResultList();
	}

	@Override
	public void deleteById(int id) {
		User p = em.find(User.class, id);
		em.remove(p);
	}
	
	@Override
	public List<User> findByEmail(String email) {
		Query query = em.createQuery("Select u from User as u where u.email=:email");
		query.setParameter("email", email);
		return query.getResultList();
	}

}
