package com.ecommerce.orderapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orderapi.exception.OrderException;
import com.ecommerce.orderapi.feignClient.CartClient;
import com.ecommerce.orderapi.feignClient.UserClient;
import com.ecommerce.orderapi.model.Order;
import com.ecommerce.orderapi.model.OrderItem;
import com.ecommerce.orderapi.model.User;
import com.ecommerce.orderapi.service.OrderService;


@RestController
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartClient cartClient;

    @PostMapping(value = "/order/{userId}")
    public ResponseEntity<Order> saveOrder(
            @PathVariable("userId") Long userId,
            @RequestHeader(value = "Cookie") Long cartId) {

        Order cart = cartClient.getCart(cartId);

        User user  = userClient.getUserById(userId);
        
        if (cart != null && user != null) {
            Order order = orderService.createOrder(user, cart);
            try {
                orderService.saveOrder(order);
                cartClient.deleteCart(cartId);
                return new ResponseEntity<>(order, HttpStatus.CREATED);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/order/{orderId}/confirm")
    public ResponseEntity<Order> ConfirmOrderHandler(@PathVariable Long orderId)
    throws OrderException{
        
        Order order = orderService.confirmedOrder(orderId);
        
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/ship")
    public ResponseEntity<Order> ShippedOrderHandler(@PathVariable Long orderId)
    throws OrderException{
        
        Order order = orderService.shippedOrder(orderId);
        
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/deliver")
    public ResponseEntity<Order> DeliveredOrderHandler(@PathVariable Long orderId)
    throws OrderException{
        
        Order order = orderService.deliveredOrder(orderId);
        
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/cancel")
    public ResponseEntity<Order> CancelOrderHandler(@PathVariable Long orderId)
    throws OrderException{
        
        Order order = orderService.cancelledOrder(orderId);
        
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/order/{orderId}/delete")
    public ResponseEntity<String> DeleteOrderHandler(@PathVariable Long orderId)
    throws OrderException{
        
        orderService.deleteOrder(orderId);
        

        return new ResponseEntity<>("Order Deleted", HttpStatus.OK);
    }


}
