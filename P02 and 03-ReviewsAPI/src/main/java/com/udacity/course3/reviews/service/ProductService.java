package com.udacity.course3.reviews.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll()
		.forEach(products::add);
		
		return products;
	}
	
	public Product getProduct(int id) {		
		return productRepository.findById(id).get();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void updateProduct(String id, Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

}
