package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udacity.course3.reviews.model.Review;

public interface ReviewRepository extends CrudRepository<Review,Integer>{
	
	public List<Review> findByProductId(int id);
	
}
