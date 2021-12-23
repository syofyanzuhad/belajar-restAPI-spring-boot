package com.domain.model.repos;

import com.domain.model.entities.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
    
}
