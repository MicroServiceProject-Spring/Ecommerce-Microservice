package com.arman.ephify.service;


import com.arman.ephify.dto.ProductRequest;
import com.arman.ephify.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest) throws Exception;

    List<ProductResponse> getAllProducts();
}
