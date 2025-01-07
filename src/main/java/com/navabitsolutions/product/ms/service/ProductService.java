package com.navabitsolutions.product.ms.service;

import com.navabitsolutions.product.ms.request.ProductRequest;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(ProductRequest productRequest) throws ExecutionException, InterruptedException;
}
