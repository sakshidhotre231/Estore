package com.wipro.bankofamerica.estore.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.repo.UserRepository;
import com.wipro.bankofamerica.estore.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return repository.findAll();
	}

	@Override
	public List<User> getListByCity(String city) {
		return repository.findByCity(city);
	}

	@Override
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User loginUser(String username, String password) {
		User user = repository.findByUsername(username);
		if(user == null) {
			throw new RuntimeException("Given Username is not found");
		}else {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
			throw new RuntimeException("Incorrect credentials...");
		}
	}

}
