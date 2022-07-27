package com.example.challengeapplication.Activities;

import static com.example.challengeapplication.Models.StaticVariable.order;
import static com.example.challengeapplication.Models.StaticVariable.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

public class ProductActivity extends AppCompatActivity {

    RelativeLayout navShop, navCart, minus, plus;
    ImageView img_prod;
    TextView titleProd, priceProd, descProd, quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        navShop = findViewById(R.id.nav_shop);
        navCart = findViewById(R.id.nav_cart);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        img_prod = findViewById(R.id.img_prod);
        titleProd = findViewById(R.id.titleProd);
        priceProd = findViewById(R.id.priceProd);
        descProd = findViewById(R.id.descProd);
        quantity = findViewById(R.id.quantity);

        navShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        navCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StaticVariable.order.getProducts().size() == 0) {
                    Toast.makeText(ProductActivity.this, "There is no products for this order !", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
                startActivity(new Intent(ProductActivity.this, OrderActivity.class));
            }
        });

        img_prod.setImageResource(product.getImg());
        titleProd.setText(product.getName());
        descProd.setText(product.getDescription());
        priceProd.setText("£" + product.getPriceUnity());
        quantity.setText(String.valueOf(product.getQuantity()));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getQuantity() == 0) {
                    product.setInOrder(true);
                    order.getProducts().add(product);
                }
                product.setQuantity(product.getQuantity() + 1);
                product.calculateSubtotal();
                quantity.setText(String.valueOf(product.getQuantity()));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product.getQuantity() == 0)
                    return;
                if (product.getQuantity() == 1) {
                    order.getProducts().remove(product);
                    product.setInOrder(false);
                }
                product.setQuantity(product.getQuantity() - 1);
                product.calculateSubtotal();
                quantity.setText(String.valueOf(product.getQuantity()));
            }
        });
    }
}