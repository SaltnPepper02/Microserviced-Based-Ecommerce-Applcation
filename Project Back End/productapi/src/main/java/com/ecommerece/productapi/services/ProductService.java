package com.ecommerece.productapi.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecommerece.productapi.exception.ProductException;
import com.ecommerece.productapi.model.Product;
import com.ecommerece.productapi.request.CreateProductRequest;

public interface ProductService {
    public Product createProduct(CreateProductRequest productRequest);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product updatedProductRequest) throws ProductException;

    public Product findProductById(Long productId) throws ProductException;

    public Page<Product> findProductsByCategory(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, String sort, String stock,
        Integer pageNumber, Integer pageSize);

    public List<Product> findAllProducts() throws ProductException;
}
