package com.wipro.bankofamerica.estore.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.wipro.bankofamerica.estore.model.Product;
import com.wipro.bankofamerica.estore.repo.ProductRepo;
import com.wipro.bankofamerica.estore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getProductById(Integer productId) {
		Optional<Product> product = productRepo.findByProductId(productId);//102,110
		if(product.isPresent()) {
			return product;
		}else {
			throw new RuntimeException("Given Product is not available : " + productId);
		}
	}

	@Override
	public void deleteById(Integer Id) {
		productRepo.deleteById(Id);
	}

	
	//update
	@Override
	public Product updateProductById(Integer id, Product product) {
		Optional<Product> prdt = productRepo.findById(id);
		if(prdt.isPresent()) {
			Product product2 = prdt.get();
			product2.setProductId(product.getProductId());//101-102
			product2.setProductName(product.getProductName());//Mouse
			product2.setProductDescription(product.getProductDescription());//This is mouse
			product2.setQuantity(product.getQuantity());//60
			product2.setAmount(product.getAmount());//120-150
			product2.setStatus(product.getStatus());//isAvaiable 
			return productRepo.save(product2);
		}else {
			throw new RuntimeException("Product not found with given id :: " + id);
		}
	}

	@Override
	public ByteArrayInputStream exportProductsToExcel() throws IOException {
		try (Workbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {
		Sheet createSheet = workbook.createSheet();
		Row headerRow = createSheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("productId");
		headerRow.createCell(1).setCellValue("productName");
		headerRow.createCell(2).setCellValue("productDescription");
		headerRow.createCell(3).setCellValue("quantity");
		headerRow.createCell(4).setCellValue("amount");
		headerRow.createCell(5).setCellValue("status");
		
		List<Product> products = (List<Product>) productRepo.findAll();
		int rowInd = 1;
		
		for(Product product : products) {
			
			Row row = createSheet.createRow(rowInd++);
			row.createCell(0).setCellValue(product.getProductId());
			row.createCell(1).setCellValue(product.getProductName());
			row.createCell(2).setCellValue(product.getProductDescription());
			row.createCell(3).setCellValue(product.getQuantity());
			row.createCell(4).setCellValue(product.getAmount());
			row.createCell(5).setCellValue(product.getStatus());
		}
		workbook.write(arrayOutputStream);
		return new ByteArrayInputStream(arrayOutputStream.toByteArray());
		
		}catch (Exception e) {
			throw new RuntimeException("Failed to export data to excel : " + e);
		}
		
		
	}

	@Override
	public ByteArrayInputStream exportProductsToPDF() {
		// TODO Auto-generated method stub
		return null;
	}


}
