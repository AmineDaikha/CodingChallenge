package com.example.challengeapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.challengeapplication.Adapters.RecyclerOrderAdapter;
import com.example.challengeapplication.Models.Order;
import com.example.challengeapplication.Models.Product;
import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {


    RelativeLayout navReturn;
    TextView subtotal, discount, total;
    Button confOrder;
    RecyclerOrderAdapter recyclerOrderAdapter;
    RecyclerView recyclerOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerOrder = findViewById(R.id.listOrder);
        navReturn = findViewById(R.id.nav_return);
        subtotal = findViewById(R.id.subtotal);
        discount = findViewById(R.id.discount);
        total = findViewById(R.id.total);
        confOrder = findViewById(R.id.confOrder);

        confOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adb = new AlertDialog.Builder(OrderActivity.this);
                adb.setTitle("Confirmation");
                adb.setMessage("Are you sure to send the order?");
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes",
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                newOrder();
                            }
                        }).show();
            }
        });

        navReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newOrder();
            }
        });
        setupRecyclerOrdersView();
    }

    private void newOrder() {
        StaticVariable.order = new Order();
        StaticVariable.product = new Product();
        StaticVariable.categories = new ArrayList<>();
        StaticVariable.products = new ArrayList<>();
        finish();
        startActivity(new Intent(OrderActivity.this, MainActivity.class));
    }

    private void setupRecyclerOrdersView() {
        recyclerOrderAdapter = new RecyclerOrderAdapter(this, StaticVariable.order);
        recyclerOrderAdapter.setSubtotal(subtotal);
        recyclerOrderAdapter.setDiscount(discount);
        recyclerOrderAdapter.setTotal(total);
        recyclerOrderAdapter.setActivity(this);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));

        for (Product product : StaticVariable.order.getProducts()) {
            if (product.getId() == 1 && (int) (product.getQuantity() / 2) > 0) {
                int bonusOf3 = product.getQuantity() / 2;
                for (Product productBreat : StaticVariable.order.getProducts()) {
                    if (productBreat.getId() == 3) {
                        double discountBreat = bonusOf3 * Double.parseDouble(productBreat.getPriceUnity().multiply(new BigDecimal("0.50")).toString());
                        productBreat.setDiscount(BigDecimal.valueOf(discountBreat));
                        System.out.println("discount is : " + discountBreat);
                    }
                }
            }
            if (product.getId() == 2 && (int) (product.getQuantity() % 4) == 0) {
                int bonusOf2 = (int) product.getQuantity() / 4;
                product.setDiscount(BigDecimal.valueOf(bonusOf2).multiply(product.getPriceUnity()));
                System.out.println("discount of milk  is : " + product.getDiscount());
            }
        }
        StaticVariable.order.calculateSubtotal();
        StaticVariable.order.calculateDiscount();
        StaticVariable.order.calculateTotal();
        subtotal.setText(String.valueOf(StaticVariable.order.getSubtotal()));
        discount.setText(String.valueOf(StaticVariable.order.getDiscount()));
        total.setText(String.valueOf(StaticVariable.order.getTotal()));

        recyclerOrder.setAdapter(recyclerOrderAdapter);
        recyclerOrderAdapter.setRecyclerOrder(recyclerOrder);
    }
}