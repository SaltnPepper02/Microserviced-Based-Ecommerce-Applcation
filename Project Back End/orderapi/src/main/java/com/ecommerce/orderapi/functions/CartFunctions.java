package com.ecommerce.orderapi.functions;

import java.math.BigDecimal;

import com.ecommerce.orderapi.model.Product;

public class CartFunctions {

    public static BigDecimal getSubTotalForItem(Product product, int quantity){
        return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
