package com.ecommerce.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orderapi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
