package com.productservice.ProductService.service;

import com.productservice.ProductService.dto.ProductRequest;
import com.productservice.ProductService.dto.ProductResponse;
import com.productservice.ProductService.model.Product;
import com.productservice.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //instead creating constructor manually
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product {} is saved",product.getId());

    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::productToProductResponse).toList();
    }

    private ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
