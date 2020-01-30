package com.udacity.course3.reviews.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> getAllComments(int ReviewId){
		List<Comment> comments = new ArrayList<>();
		commentRepository.findByReviewId(ReviewId)
		.forEach(comments::add);
		
		return comments;
	}
	
	public Comment getComment(int id) {		
		return commentRepository.findById(id).get();
	}

	public void addComment(Comment comment) {
		commentRepository.save(comment);
	}

	public void updateComment(Comment comment) {
		commentRepository.save(comment);
	} 

	public void deleteComment(int id) {
		commentRepository.deleteById(id);
	}
	
}
