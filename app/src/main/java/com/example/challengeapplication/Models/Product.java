package com.example.challengeapplication.Models;

import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private int id;
    private String name;
    private String description;
    private Category category;
    private int seq;
    private int img;
    private int quantity = 0;
    boolean inOrder = false;
    private BigDecimal priceUnity = new BigDecimal("250.00");
    private BigDecimal subtotal = new BigDecimal("0.00");
    private BigDecimal discount = new BigDecimal("0.00");
    private BigDecimal total = new BigDecimal("0.00");
    private TextView discountTxt;

    public Product() {

    }

    public Product(int id, String name, Category idCategory, int img, String description, BigDecimal priceUnity) {
        this.id = id;
        this.name = name;
        this.category = idCategory;
        this.img = img;
        this.description = description;
        this.priceUnity = priceUnity;
    }

    public Product(int id, String name, Category idCategory, int img, String description, int quantity, BigDecimal priceUnity) {
        this.id = id;
        this.name = name;
        this.category = idCategory;
        this.img = img;
        this.description = description;
        this.quantity = quantity;
        this.priceUnity = priceUnity;
        calculateSubtotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceUnity() {
        return priceUnity;
    }

    public void setPriceUnity(BigDecimal priceUnity) {
        this.priceUnity = priceUnity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void calculateSubtotal() {
        subtotal = priceUnity.multiply(BigDecimal.valueOf(quantity));
    }

    public void calculateTotal() {
        total = subtotal.subtract(discount);
    }

    public boolean isInOrder() {
        return inOrder;
    }

    public void setInOrder(boolean inOrder) {
        this.inOrder = inOrder;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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

    public TextView getDiscountTxt() {
        return discountTxt;
    }

    public void setDiscountTxt(TextView discountTxt) {
        this.discountTxt = discountTxt;
    }
}
