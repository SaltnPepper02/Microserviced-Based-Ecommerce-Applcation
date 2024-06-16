package com.ecommerce.orderapi.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.orderapi.model.Order;

@FeignClient(name = "cartapi", url = "${CART_API_URL:http://localhost:8005}/api")
public interface CartClient {
    
    @GetMapping(value = "/cart")
    public Order getCart(@RequestHeader(value = "Cookie") Long cartId);

    @DeleteMapping(value = "/cart/delete-cart")
    public String deleteCart(@RequestHeader(value = "Cookie") Long cartId);
    
}
