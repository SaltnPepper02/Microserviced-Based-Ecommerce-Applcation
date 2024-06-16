package com.ecommerce.orderapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orderapi.exception.OrderException;
import com.ecommerce.orderapi.functions.OrderFunctions;
import com.ecommerce.orderapi.model.Order;
import com.ecommerce.orderapi.model.OrderItem;
import com.ecommerce.orderapi.model.User;
import com.ecommerce.orderapi.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findById(orderId);

        if(opt.isPresent()){
            return opt.get();
        }

        throw new OrderException("Order does not exist");
    }

    @Override
    public Order createOrder(User user, Order order) {

        order.setUser(user);
        order.setOrderedDate(LocalDate.now());
        order.setStatus("PENDING");
        return order;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("PLACED");
        //order.getPaymentInfo().setPaymentStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("CONFIRMED");
        return order;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("SHIPPED");
        return order;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("DELIVERED");
        return order;
    }

    @Override
    public Order cancelledOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus("CANCELLED");
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        orderRepository.deleteById(orderId);
    }

}
