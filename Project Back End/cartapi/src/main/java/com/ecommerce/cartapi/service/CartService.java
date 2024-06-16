package com.ecommerce.cartapi.service;

import java.util.List;

import com.ecommerce.cartapi.exceptions.ProductException;
import com.ecommerce.cartapi.model.Cart;
import com.ecommerce.cartapi.model.CartItem;

public interface CartService {
    public Cart createCart(Long cartId);
    public CartItem addItemToCart(Long cartId, Long productId, Integer quantity) throws ProductException;
    public Cart getCart(Long cartId);
    public void changeItemQuantity(Long cartId, Long productId, Integer quantity) throws ProductException;
    public void deleteItemFromCart(Long cartId, Long productId);
    public List<CartItem> getAllItemsFromCart(Long cartId);
    public boolean checkIfItemIsExist(Long cartId, Long productId);
    public void deleteCart(Long cartId);
}
