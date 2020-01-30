package com.udacity.course3.reviews.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReviewWithEmbeddedComments {
 	private Integer id;
	private String review;
	private List<String> Comments;
	
	public ReviewWithEmbeddedComments() {
		
	}
	
	public ReviewWithEmbeddedComments(Integer id, String review) {
		super();
		this.id = id;
		this.review = review;
		this.Comments = new ArrayList<>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public void addComment(String comment) {
		this.Comments.add(comment);
	}
	

}

