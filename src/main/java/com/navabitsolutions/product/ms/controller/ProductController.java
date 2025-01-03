package com.navabitsolutions.product.ms.controller;

import com.navabitsolutions.product.ms.request.ProductRequest;
import com.navabitsolutions.product.ms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
        String productId = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}