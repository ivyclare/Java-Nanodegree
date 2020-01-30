package com.udacity.course3.reviews.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> getAllReviews(int productId){
		List<Review> reviews = new ArrayList<>();
		reviewRepository.findByProductId(productId)
		.forEach(reviews::add);
		
		return reviews;
	}
	
	
	public Review getReview(int reviewId) {		
		return reviewRepository.findById(reviewId).get();
	}

	public void addReview(Review review) {
		reviewRepository.save(review);
	}

	public void updateReview(Review review) {
		reviewRepository.save(review);
	} 

	public void deleteReview(int id) {
		reviewRepository.deleteById(id);
	}
	

}
