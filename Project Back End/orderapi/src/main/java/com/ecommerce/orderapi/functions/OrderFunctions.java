package com.ecommerce.orderapi.functions;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.orderapi.model.OrderItem;

public class OrderFunctions {

    public static BigDecimal countTotalPrice(List<OrderItem> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total = total.add(cart.get(i).getSubTotal());
        }
        return total;
    }
    
}
