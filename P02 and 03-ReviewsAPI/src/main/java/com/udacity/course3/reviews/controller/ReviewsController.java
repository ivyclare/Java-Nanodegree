package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.udacity.course3.reviews.document.ReviewWithEmbeddedComments;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepositoryMongo;
import com.udacity.course3.reviews.service.ProductService;
import com.udacity.course3.reviews.service.ReviewService;
import com.udacity.course3.reviews.service.ReviewServiceMongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ProductService productService;
	//Autowire Mongo Repository
	@Autowired
	private ReviewServiceMongo reviewServiceMongo;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ReviewRepositoryMongo reviewRepositoryMongo;
    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
	public ResponseEntity<?> createReviewForProduct(@RequestBody @Valid String prodReview,@PathVariable @Valid Integer productId) {
    	
    	//Check if product exist    	
    	Product prod = productService.getProduct(productId);
        if (prod != null) {
        	//save review if product exist
        	Review review = new Review();
        	review.setProduct(new Product(productId,"", "",""));
        	review.setReview(prodReview);
    		reviewService.addReview(review);
    		
    		ReviewWithEmbeddedComments reviewWithEmbeddedComments =  new ReviewWithEmbeddedComments(review.getId(),prodReview);	
            reviewServiceMongo.addReview(reviewWithEmbeddedComments);
    		    		
    		return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //throw new NotFoundException("Applicants not found");
        }
	}
    
    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listReviewsForProduct(@PathVariable("productId") @Valid int productId) {
    		
    	Optional<Product>  prod = productRepository.findById(productId);
    	
    	if (!prod.isPresent()|| prod.get().getReviews().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
    	}
    	//List<Review> review_list = reviewService.getAllReviews(productId);
    	
    	return new ResponseEntity<List<String>>(reviewServiceMongo.getAllReviews(prod), HttpStatus.NOT_FOUND);
    }
    
    
}