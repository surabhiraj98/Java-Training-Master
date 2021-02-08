package com.rakuten.training.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Greeting;

@RestController
public class GreetingController {
	
	List<Greeting> allGreetings = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		allGreetings.add(new Greeting("morning","Good Morning"));
		allGreetings.add(new Greeting("afternoon","Good Afternoon"));
		allGreetings.add(new Greeting("evening","Good Evening"));
		allGreetings.add(new Greeting("night","Good Night"));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/greetings")
	public List<Greeting> getAllGreetings() {
		return allGreetings;
	}
	
	@PostMapping("/greetings")
	public void addGreeting(@RequestBody Greeting toBeAdded) {
		allGreetings.add(toBeAdded);
	}
}
