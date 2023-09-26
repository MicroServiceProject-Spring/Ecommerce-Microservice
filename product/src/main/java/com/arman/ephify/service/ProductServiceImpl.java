package com.arman.ephify.service;

import com.arman.ephify.model.Product;
import com.arman.ephify.dto.ProductRequest;
import com.arman.ephify.dto.ProductResponse;
import com.arman.ephify.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws Exception {
        try {
            Product product = Product.builder()
                    .description(productRequest.getDescription())
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .build();
            productRepository.save(product);
            log.info("Product id:{} has been saved successfully",product.getId());
            return getProductResponse(product);
        } catch (Exception e) {
            log.error("Unable to create product with request {}", productRequest);
            throw new Exception("Unable to create the product with exception:{}", e.getCause());
        }

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::getProductResponse).toList();
    }

    private ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(String.valueOf(product.getId()))
                .description(product.getDescription())
                .price(product.getPrice().toBigInteger())
                .name(product.getName())
                .build();
    }
}
