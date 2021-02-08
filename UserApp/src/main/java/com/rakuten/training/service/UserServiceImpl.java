package com.rakuten.training.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.dal.UserDAO;
import com.rakuten.training.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	UserDAO dao;

	@Autowired
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@Override
	public int createNewUser(User toBeCreated) {
		try {
			String pw_regexString = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
			Pattern pattern = Pattern.compile(pw_regexString);
			Matcher matcher = pattern.matcher(toBeCreated.getPassword());
			if (matcher.matches() == false) {
				throw new IllegalArgumentException("Password not according to standard");
			} else if (findByEmail(toBeCreated.getEmail()).size() > 0) {
				throw new IllegalArgumentException("Email Already Exists!");
			} else {
				User created = dao.save(toBeCreated);
				return created.getId();
			}
		} catch (ConstraintViolationException e) {
			throw new IllegalArgumentException("Email ID Provided is already in use");
		}
	}

	@Override
	public void removeExisting(int id) {
		User existing = dao.findById(id);
		if (existing == null) {
			throw new IllegalArgumentException("User with id " + id + " not found!");
		}
		dao.deleteById(id);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<User> findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
