package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;

public interface CommentRepository  extends CrudRepository<Comment,Integer>{
	
	public List<Comment> findByReviewId(int id);
	
}