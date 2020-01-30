package com.udacity.course3.reviews.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.document.ReviewWithEmbeddedComments;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ReviewRepositoryMongo;

@Service
public class ReviewServiceMongo {
	@Autowired
	private ReviewRepositoryMongo reviewRepositoryMongo;
	
	public List<String> getAllReviews(Optional<Product> prod){
		List<String> reviewsList = new ArrayList<>();
		
		for (Review r :prod.get().getReviews()) {
    		reviewsList.add((reviewRepositoryMongo.findById(r.getId())).getReview());
		}
       		
		return reviewsList;
	}
	
	public void addReview(ReviewWithEmbeddedComments reviewWithEmbeddedComments) {
		reviewRepositoryMongo.save(reviewWithEmbeddedComments);
	}

	
}
