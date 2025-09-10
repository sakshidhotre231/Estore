package com.wipro.bankofamerica.estore.service;

import java.util.List;

import com.wipro.bankofamerica.estore.model.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User loginUser(String username, String password);
	
	public List<User> getAllUser();
	
	public List<User> getListByCity(String city);
	
	public User getUserByUsername(String username);
	

}
