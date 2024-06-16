package com.ecommerce.cartapi.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.cartapi.model.Product;

@FeignClient(name = "productapi", url = "${PRODUCT_API_URL:http://localhost:8001}") 
public interface ProductClient {

    @GetMapping(value = "/products/{productId}")
    public Product getProductById(@PathVariable(value = "productId")Long productId);
    
    

}
