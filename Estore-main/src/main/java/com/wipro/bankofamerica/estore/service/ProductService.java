package com.wipro.bankofamerica.estore.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.wipro.bankofamerica.estore.model.Product;

public interface ProductService {
	
	public Product saveProduct(Product product);
	public List<Product> getAllProduct();
	public Optional<Product> getProductById(Integer productId);
	public void deleteById(Integer Id);
	//Update
	public Product updateProductById(Integer id, Product product);
	
	ByteArrayInputStream exportProductsToExcel() throws IOException;
	ByteArrayInputStream exportProductsToPDF();
	
	

}
