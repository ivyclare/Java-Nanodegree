package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.service.ProductService;


import java.util.List;

import javax.validation.Valid;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // I have wired the services here and services have wired the repositories
	@Autowired
	private ProductService productService;
	
    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
 	public void createProduct(@RequestBody @Valid Product product) {
 		productService.addProduct(product);
 	}

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.s
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") @Valid Integer id) {
        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        Product prod = productService.getProduct(id);
        if (prod != null) {
        	return ResponseEntity.ok().body(prod);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //throw new NotFoundException("Applicants not found");
        }
        
    }
    
    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> listProducts() {
    	
		return productService.getAllProducts();
        
    }
        
}