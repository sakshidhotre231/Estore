package com.wipro.bankofamerica.estore.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.model.Product;
import com.wipro.bankofamerica.estore.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product saveProduct = productService.saveProduct(product);
		return ResponseEntity.ok(saveProduct);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProduct = productService.getAllProduct();
		return ResponseEntity.ok(allProduct);
	}
	
	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("productId") Integer productId) {
		Optional<Product> productById = productService.getProductById(productId);
		return ResponseEntity.ok(productById);
	}
	
	@DeleteMapping("/deleteById/{Id}")
	public void deleteProductById(@PathVariable("Id") Integer Id) {
		productService.deleteById(Id);
		System.out.println("Deleted Succefully...");
	}
	
	//update API
	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
		Product updateProductById = productService.updateProductById(id, product);
		return ResponseEntity.ok(updateProductById);
	}
	
	
	//To get Excel of Products
	@GetMapping("api/products/download/excel")
	public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
		ByteArrayInputStream in = productService.exportProductsToExcel();
		
		//Prepare headers for file download
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Desposition", "attachment; filename=product.xlsx");
		
		InputStreamResource resource = new InputStreamResource(in);
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(resource);
	
	}
	

}
