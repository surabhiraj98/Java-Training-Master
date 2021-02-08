package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.User;

public interface UserService {

	public int createNewUser(User toBeCreated);

	public void removeExisting(int id);

	public List<User> findAll();

	public User findById(int id);
	
	public List<User> findByEmail(String email);

}
