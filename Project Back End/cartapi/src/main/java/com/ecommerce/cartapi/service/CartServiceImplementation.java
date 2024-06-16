package com.ecommerce.cartapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.cartapi.exceptions.ProductException;
import com.ecommerce.cartapi.feignClient.ProductClient;
import com.ecommerce.cartapi.model.Cart;
import com.ecommerce.cartapi.model.CartItem;
import com.ecommerce.cartapi.model.Product;
import com.ecommerce.cartapi.repository.CartItemRepository;
import com.ecommerce.cartapi.repository.CartRepository;

import feign.FeignException;

import com.ecommerce.cartapi.function.CartFunctions;

@Service
public class CartServiceImplementation implements CartService{

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private static final Logger logger = LogManager.getLogger(CartServiceImplementation.class);

    @Override
    public Cart createCart(Long cartId) {
        Cart cart = new Cart();

        cart.setId(cartId);

        return cartRepository.save(cart);
    }

    @Override
    public CartItem addItemToCart(Long cartId, Long productId, Integer quantity) throws ProductException{
        Cart cart = cartRepository.findById(cartId).orElse(null);

        // try {
        //     Product product = productClient.getProductById(productId);
        // } catch (FeignException e) {
        //     logger.error("ResponseBody: " + e.contentUTF8());
        // }

        Product product = productClient.getProductById(productId);
        product.setPid(productId);

        CartItem cartItem = new CartItem();

        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setSubTotal(CartFunctions.getSubTotalForItem(product, quantity));
        
        CartItem createdCartItem = cartItemRepository.save(cartItem);

        //cart.getItems().add(createdCartItem);

        return createdCartItem;

    }

    @Override
    public Cart getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        cart.setItems(cartItems);
        cart.setTotal(CartFunctions.countTotalPrice(cartItems));

        Cart savedCart = cartRepository.save(cart);

        return savedCart;


    }

    @Override
    public void changeItemQuantity(Long cartId, Long productId, Integer quantity) throws ProductException {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        for(CartItem item : cartItems){
            if((item.getProduct().getPid()).equals(productId)){
                item.setQuantity(quantity);
                item.setSubTotal(CartFunctions.getSubTotalForItem(item.getProduct(), quantity));
                cartItemRepository.save(item);
            }
        }


    }

    @Override
    public void deleteItemFromCart(Long cartId, Long productId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        for(CartItem item : cartItems){
            if((item.getProduct().getPid()).equals(productId)){
                cartItemRepository.deleteById(item.getId()); // 
            }
        }

    }


    @Override
    public List<CartItem> getAllItemsFromCart(Long cartId) {
        
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        
        return cartItems;
    }

    @Override
    public void deleteCart(Long cartId) {
        //cartItemRepository.deleteByCartId(cartId);
        cartRepository.deleteById(cartId);
    }

    @Override
    public boolean checkIfItemIsExist(Long cartId, Long productId) {
        List<CartItem> cart = getAllItemsFromCart(cartId);
        for(CartItem item : cart){
            if((item.getProduct().getPid().equals(productId))){
                return true;
            }
        }
        return false;
    }

    
}
