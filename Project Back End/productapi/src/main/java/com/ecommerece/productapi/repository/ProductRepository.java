package com.ecommerece.productapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerece.productapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @Query("SELECT p FROM Product p " +
        "WHERE (p.category.name = :category OR :category='') " +
        "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.price BETWEEN :minPrice AND :maxPrice)) " +
        "ORDER BY " +
        "CASE WHEN :sort = 'price_low' THEN p.price END ASC, " +
        "CASE WHEN :sort = 'price_high' THEN p.price END DESC")
    public List<Product> filterProducts(@Param("category")String category,
        @Param("minPrice") Integer minPrice, 
        @Param("maxPrice")Integer maxPrice,
        @Param("sort") String sort);
}
