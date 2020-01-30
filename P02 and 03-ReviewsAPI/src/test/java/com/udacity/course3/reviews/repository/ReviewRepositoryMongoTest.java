package com.udacity.course3.reviews.repository;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import com.udacity.course3.reviews.document.ReviewWithEmbeddedComments;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.service.ReviewServiceMongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest()
@ExtendWith(SpringExtension.class)
public class ReviewRepositoryMongoTest  {
		 
	 @Autowired
	 private ReviewRepositoryMongo reviewRepositoryMongo;
	 @Autowired
	 MongoTemplate mongoTemplate;
	
	 private final static List<String> PRODUCT_ID_LIST = Arrays.asList("1", "2");

	 private static final Random RANDOM = new Random();
	 
	  
	 @Test
	 public void ReviewWithEmbeddedCommentsTest() {
	        long now = System.currentTimeMillis();
	        int productId = 1;
	        String prodReview = "This is a test review";
	        
	        Review review = new Review();
        	review.setProduct(new Product(productId,"", "",""));
        	review.setId(1);
        	review.setReview(prodReview);
    		
    		ReviewWithEmbeddedComments reviewWithEmbeddedComments =  new ReviewWithEmbeddedComments(review.getId(),prodReview);	
            reviewRepositoryMongo.save(reviewWithEmbeddedComments);
	        List<String> reviewsList = new ArrayList<>();	    		                
	        reviewsList.add((reviewRepositoryMongo.findById(review.getId())).getReview());
	        
	        assertThat(reviewsList).isNotEmpty(); 
    }
	 
	 
	 @Test
	 public void testEmbeddedMongoDb() {
		 
	        DBObject objectToSave = BasicDBObjectBuilder.start().add("key", "value").get();

	        mongoTemplate.save(objectToSave, "collection");

	        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).isNotEmpty();
	    }
	

}