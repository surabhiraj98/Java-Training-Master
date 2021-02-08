package com.rakuten.training.web;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.User;
import com.rakuten.training.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/users")
	public ResponseEntity getAllUsers() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{idPathVariable}")
	public ResponseEntity getUserById(@PathVariable("idPathVariable") int id) {
		User user = service.findById(id);
		if (user!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity createNewUser(@RequestBody User toBeCreated){
		try {
			int id = service.createNewUser(toBeCreated);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity<>(toBeCreated, headers, HttpStatus.CREATED);
		}catch (IllegalArgumentException e) {
			RegistrationError error = new RegistrationError(e.getMessage());
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
//		catch (DataIntegrityViolationException e) {
//			RegistrationError error = new RegistrationError(toBeCreated.getEmail(),"Email Provided is already in use");
//			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//		}
	}
	
	@DeleteMapping("/users/{idPathVariable}")
	public ResponseEntity deleteUser(@PathVariable("idPathVariable") int id) {
		try {
			service.removeExisting(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
