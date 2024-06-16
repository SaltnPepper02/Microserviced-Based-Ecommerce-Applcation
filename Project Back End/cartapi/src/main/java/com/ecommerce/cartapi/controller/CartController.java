package com.ecommerce.cartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cartapi.exceptions.ProductException;
import com.ecommerce.cartapi.model.Cart;
import com.ecommerce.cartapi.model.CartItem;
import com.ecommerce.cartapi.repository.CartRepository;
import com.ecommerce.cartapi.service.CartService;


@RestController
@RequestMapping("/api")
public class CartController {
    
    @Autowired
    CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(value = "/cartitem")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader(value = "Cookie") Long cartId) {
        List<CartItem> cart = cartService.getAllItemsFromCart(cartId);

        if (!cart.isEmpty()) {
            return new ResponseEntity<>(
                    cart,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cart")
    public ResponseEntity<Cart> getCart(@RequestHeader(value = "Cookie") Long cartId) {
        Cart cart = cartService.getCart(cartId);

        if (cart != null) {
            return new ResponseEntity<>(
                    cart,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/cart/add", params = { "productId", "quantity" })
    public ResponseEntity<String> addItemToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestHeader(value = "Cookie") Long cartId) throws ProductException {
        
        Cart existingCart = cartRepository.findById(cartId).orElse(null);

        if(existingCart == null ){
            cartService.createCart(cartId);
        }

        List<CartItem> cart = cartService.getAllItemsFromCart(cartId);

        if(cart == null){
            CartItem cartItem = cartService.addItemToCart(cartId, productId, quantity);
        } else{
            if (cartService.checkIfItemIsExist(cartId, productId)) {
                cartService.changeItemQuantity(cartId, productId, quantity);
            } else {
                cartService.addItemToCart(cartId, productId, quantity);
            }
        }
        return new ResponseEntity<String>("Item added", HttpStatus.OK);


    }

    @DeleteMapping(value = "/cart/delete", params = { "productId" })
    public ResponseEntity<String> deleteItem(
            @RequestParam("productId") Long productId,
            @RequestHeader(value = "Cookie") Long cartId) throws ProductException {

        cartService.deleteItemFromCart(cartId, productId);

        
        return new ResponseEntity<String>("Item deleted", HttpStatus.OK);


    }

    @DeleteMapping(value = "/cart/delete-cart")
    public ResponseEntity<String> deleteCart(
            @RequestHeader(value = "Cookie") Long cartId) throws ProductException {

        cartService.deleteCart(cartId);

        return new ResponseEntity<String>("Cart deleted", HttpStatus.OK);


    }


}
