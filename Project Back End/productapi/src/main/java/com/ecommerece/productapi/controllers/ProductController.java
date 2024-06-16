package com.ecommerece.productapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerece.productapi.exception.ProductException;
import com.ecommerece.productapi.model.Product;
import com.ecommerece.productapi.request.CreateProductRequest;
import com.ecommerece.productapi.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategory(@RequestParam String category, @RequestParam List<String> color, @RequestParam List<String> size,
    @RequestParam Integer minPrice, @RequestParam Integer maxPrice, @RequestParam String sort, @RequestParam String stock, @RequestParam Integer pageNumber,
    @RequestParam Integer pageSize){

        Page<Product> response = productService.findProductsByCategory(category, color, size, minPrice, maxPrice, sort, stock, pageNumber, pageSize);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable ("productId") Long productId) throws ProductException{
        
        Product product = productService.findProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/all")
    public ResponseEntity<List<Product>> findAllProducts() throws ProductException{

        List<Product> response = productService.findAllProducts();

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        
        Product product = productService.createProduct(request);
        
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) throws ProductException{

        productService.deleteProduct(productId);

        return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
    }

    // @PostMapping("/products/all")
    // public ResponseEntity<List<Product>> findAllProduct() throws ProductException {
        
    //     List<Product> products = productService.findAllProducts();
        
    //     return new ResponseEntity<>(products, HttpStatus.CREATED);
    // }

    @PutMapping("/{productId}/update")
    public  ResponseEntity<Product> updateProduct(@RequestBody Product request, @PathVariable Long productId) throws ProductException{
        
        Product product = productService.updateProduct(productId, request);

        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/products/creates")
    public  ResponseEntity<String> createMultipleProduct(@RequestBody CreateProductRequest[] requests){

        for(CreateProductRequest product: requests){
            productService.createProduct(product);
        }
    

        return new ResponseEntity<>("Products Created", HttpStatus.CREATED);
    }
}
