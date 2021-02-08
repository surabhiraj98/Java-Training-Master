package com.rakuten.training.dal;

import java.util.List;

import com.rakuten.training.domain.User;

public interface UserDAO {
	
	public User save(User toBeSaved);
	
	public User findById(int id);
	
	public List<User> findAll();
	
	public void deleteById(int id);

	public List<User> findByEmail(String email);
	
}
