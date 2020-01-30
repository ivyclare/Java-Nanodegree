package com.udacity.course3.reviews.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{

}

