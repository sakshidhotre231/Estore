package com.wipro.bankofamerica.estore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User saveUser = service.saveUser(user);
		return ResponseEntity.ok(saveUser);
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = service.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	@GetMapping("/getByCity/{city}")
	public ResponseEntity<List<User>> getUserByCity(@PathVariable("city") String city) {
		List<User> listByCity = service.getListByCity(city);
		return ResponseEntity.ok(listByCity);
	}
	
	@GetMapping("/getByUsername/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		User userByUsername = service.getUserByUsername(username);
		return ResponseEntity.ok(userByUsername);
	}

}
