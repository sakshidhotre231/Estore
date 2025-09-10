package com.wipro.bankofamerica.estore.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bankofamerica.estore.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public Optional<Product> findByProductId(Integer productId);
	
	//public Optional<Product> deleteByProductId(Integer productId);

}
