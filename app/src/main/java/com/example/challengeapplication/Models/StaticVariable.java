package com.example.challengeapplication.Models;

import android.widget.TextView;

import java.util.ArrayList;

public class StaticVariable {

    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static Order order = new Order();
    public static Product product;
    public static TextView quantityProducts;
    public static TextView quantityProductsFrag;
}
