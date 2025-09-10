package com.wipro.bankofamerica.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.service.UserService;

@RestController
@RequestMapping("/logins")
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			
			String username = user.getUsername();
			String password = user.getPassword();
			
			User loginUser = service.loginUser(username, password);
			return ResponseEntity.ok().body(loginUser);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

}
