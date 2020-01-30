package com.udacity.course3.reviews.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.udacity.course3.reviews.document.ReviewWithEmbeddedComments;


public interface ReviewRepositoryMongo extends MongoRepository<ReviewWithEmbeddedComments, String>{
	public ReviewWithEmbeddedComments findById(Integer id);
}
