package com.ecommerce.orderapi.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "items")
// @EqualsAndHashCode
public class OrderItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column (name = "quantity")
    @NotNull
    private int quantity;

    @Column (name = "subtotal")
    @NotNull
    private BigDecimal subTotal;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Order orders;

    

    public OrderItem() {
    }
    
    public OrderItem(@NotNull int quantity, @NotNull BigDecimal subTotal, Product product) {
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.product = product;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    
}
