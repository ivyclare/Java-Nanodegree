package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.document.ReviewWithEmbeddedComments;
import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ReviewRepositoryMongo;
import com.udacity.course3.reviews.service.CommentService;
import com.udacity.course3.reviews.service.ProductService;
import com.udacity.course3.reviews.service.ReviewService;

import java.util.List;

import javax.validation.Valid;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReviewRepositoryMongo reviewRepositoryMongo;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(@RequestBody @Valid String comment_text,@PathVariable @Valid int reviewId) {
    	//Check if product exist
    	Review rev = reviewService.getReview(reviewId);
        if (rev != null) {
        	//save review if product exist
        	Comment comment = new Comment();
        	comment.setComment(comment_text);
        	comment.setReview(new Review(reviewId,"",0));
    		commentService.addComment(comment);
    		
    		// Save embedded comments	
    		ReviewWithEmbeddedComments reviewWithEmbeddedComments = reviewRepositoryMongo.findById(rev.getId());
    		if( reviewWithEmbeddedComments != null ){
    			reviewWithEmbeddedComments.addComment(comment_text);
    			reviewRepositoryMongo.save(reviewWithEmbeddedComments);
    		
    		return new ResponseEntity<>(HttpStatus.CREATED);
    		}
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.        }else {
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>>  listCommentsForReview(@PathVariable("reviewId") @Valid int reviewId) {
    	Review rev = reviewService.getReview(reviewId);
        if (rev != null) {
        	List<Comment> comment_list = commentService.getAllComments(reviewId);
        	
        	return ResponseEntity.ok().body(comment_list);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	
    }
    
    
}