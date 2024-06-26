package com.bbb.shoppingapp.product.repository.mongo;

import com.bbb.shoppingapp.product.domain.Product;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
