package com.wipro.bankofamerica.estore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bankofamerica.estore.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByCity(String city);
	
	User findByUsername(String username);

}
