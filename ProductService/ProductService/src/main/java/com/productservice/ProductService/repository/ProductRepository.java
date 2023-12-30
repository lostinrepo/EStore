package com.productservice.ProductService.repository;

import com.productservice.ProductService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {

}
