package com.ecommerece.productapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerece.productapi.exception.ProductException;
import com.ecommerece.productapi.model.Category;
import com.ecommerece.productapi.model.Product;
import com.ecommerece.productapi.repository.CategoryRepository;
import com.ecommerece.productapi.repository.ProductRepository;
import com.ecommerece.productapi.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements  ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    public ProductServiceImplementation(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest productRequest) {
        Category topLevel = categoryRepository.findByName(productRequest.getTopLevelCategory());

        if(topLevel == null){
            Category toplvlCategory = new Category();
            toplvlCategory.setName(productRequest.getTopLevelCategory());
            toplvlCategory.setLevel(1);

            topLevel = categoryRepository.save(toplvlCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(productRequest.getSecondLevelCategory(), topLevel.getName());

        if(secondLevel == null){
            Category secondlvlCategory = new Category();
            secondlvlCategory.setName(productRequest.getSecondLevelCategory());
            secondlvlCategory.setLevel(2);
            secondlvlCategory.setParentCategory(topLevel);

            secondLevel = categoryRepository.save(secondlvlCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(productRequest.getThirdLevelCategory(), secondLevel.getName());

        if(thirdLevel == null){
            Category thirdlvlCategory = new Category();
            thirdlvlCategory.setName(productRequest.getThirdLevelCategory());
            thirdlvlCategory.setLevel(3);
            thirdlvlCategory.setParentCategory(secondLevel);

            thirdLevel = categoryRepository.save(thirdlvlCategory);
        }

        Product newProduct = new Product();

        newProduct.setTitle(productRequest.getTitle());
        newProduct.setColor(productRequest.getColor());
        newProduct.setDescription(productRequest.getDescription());
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setImageUrl(productRequest.getImageUrl());
        newProduct.setBrand(productRequest.getBrand());
        newProduct.setSizes(productRequest.getSize());
        newProduct.setQuantity(productRequest.getQuantity());
        newProduct.setCategory(thirdLevel);

        Product savedProduct = productRepository.save(newProduct);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);

        return ("Product Deleted Successfully");
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProductRequest) throws ProductException {
        Product product = findProductById(productId);

        if(updatedProductRequest.getQuantity() != 0){
            product.setQuantity(updatedProductRequest.getQuantity());
            product.setSizes(updatedProductRequest.getSizes());
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);

        if(opt.isPresent()){
            return opt.get();
        }

        throw new ProductException("Product with id " + productId + " is not found.." );
    }

    @Override
    public Page<Product> findProductsByCategory(String category, List<String> colors, List<String> sizes,
            Integer minPrice, Integer maxPrice, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, sort);

        if(!colors.isEmpty()){
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }

        if(stock != null){
            if(stock.equals("in_stock")){
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")){
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int)pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filterProducts = new PageImpl<>(pageContent,  pageable, products.size());

        return filterProducts;
    }

    @Override
    public List<Product> findAllProducts() throws ProductException {
        List<Product> allProducts = productRepository.findAll();

        if(!allProducts.isEmpty()){
            return allProducts;
        }

        throw new ProductException("no products can be found" );
    }
    
}
