package com.ecommerce.cartapi.function;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.cartapi.model.CartItem;
import com.ecommerce.cartapi.model.Product;

public class CartFunctions {

    public static BigDecimal getSubTotalForItem(Product product, int quantity){
        return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }

    public static BigDecimal countTotalPrice(List<CartItem> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total = total.add(cart.get(i).getSubTotal());
        }
        return total;
    }
}
