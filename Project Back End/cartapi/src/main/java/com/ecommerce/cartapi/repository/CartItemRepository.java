package com.ecommerce.cartapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cartapi.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {    
    List<CartItem> findByCartId(Long cartId);

    void deleteByCartId(Long cartId);
}
