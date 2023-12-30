package com.productservice.ProductService.controller;

import com.productservice.ProductService.dto.ProductRequest;
import com.productservice.ProductService.dto.ProductResponse;
import com.productservice.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
            productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
