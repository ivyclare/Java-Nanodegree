package com.udacity.course3.reviews.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="review")
	private String review;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
	private Product product;
	
	@OneToMany(mappedBy="review",fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonIgnore
    private Set<Comment> comments;
	
	public Review() {	
	}
		
	public Review(Integer id, String review, int productId) {
		super();
		this.id = id;
		this.review = review;
		//this.time = time;
		this.product = new Product(productId,"test","1","www");
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
         this.comments = comments;
    } 	
	
}
