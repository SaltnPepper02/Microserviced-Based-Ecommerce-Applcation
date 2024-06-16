package com.ecommerce.orderapi.service;

import java.util.List;

import com.ecommerce.orderapi.exception.OrderException;
import com.ecommerce.orderapi.model.Order;
import com.ecommerce.orderapi.model.OrderItem;
import com.ecommerce.orderapi.model.User;

public interface OrderService {
    public Order saveOrder(Order order);
    public Order createOrder(User user, Order cart);
    public Order findOrderById(Long orderId) throws OrderException;
    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancelledOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}
