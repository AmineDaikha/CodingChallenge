package com.example.challengeapplication.Models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {

    private String id;
    private ArrayList<Product> products = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void calculateSubtotal() {
        subtotal = new BigDecimal("0.00");
        for (Product food : products) {
            food.calculateSubtotal();
            subtotal = subtotal.add(food.getSubtotal());
        }
    }

    public void calculateTotal() {
        total = new BigDecimal("0.00");
        for (Product food : products) {
            food.calculateTotal();
            total = total.add(food.getTotal());
        }


    }

    public void calculateDiscount() {
        discount = new BigDecimal("0.00");
        for (Product food : products)
            discount = discount.add(food.getDiscount());
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
