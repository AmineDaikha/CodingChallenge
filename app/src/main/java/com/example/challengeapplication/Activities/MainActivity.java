package com.example.challengeapplication.Activities;

import static com.example.challengeapplication.Models.StaticVariable.order;
import static com.example.challengeapplication.Models.StaticVariable.quantityProducts;
import static com.example.challengeapplication.Models.StaticVariable.quantityProductsFrag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeapplication.Fragments.AccountFragment;
import com.example.challengeapplication.Fragments.FavFragment;
import com.example.challengeapplication.Fragments.HomeFragment;
import com.example.challengeapplication.Fragments.ShopFragment;
import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

public class MainActivity extends AppCompatActivity {

    ImageView navHome, imgCircle, navFav, navAccount;
    LinearLayout navShop;
    RelativeLayout navCart;
    TextView shopTxt, nbProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navCart = findViewById(R.id.nav_cart);
        navAccount = findViewById(R.id.nav_account);
        navFav = findViewById(R.id.nav_fav);
        navHome = findViewById(R.id.nav_home);
        navShop = findViewById(R.id.nav_shop);
        shopTxt = findViewById(R.id.shop_txt);
        imgCircle = findViewById(R.id.img_circle);
        nbProd = findViewById(R.id.nb_prod);

        quantityProducts = nbProd;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new ShopFragment());
        fragmentTransaction.commit();

        toShop();

        // test
        nbProd.setVisibility(View.GONE);

        navCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StaticVariable.order.getProducts().size() == 0) {
                    Toast.makeText(MainActivity.this, "There is no products for this order !", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });
        navShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new ShopFragment());
                fragmentTransaction.commit();
                toShop();
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
                fragmentTransaction.commit();
                toHome();
            }
        });

        navFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FavFragment());
                fragmentTransaction.commit();
                toFav();
            }
        });

        navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new AccountFragment());
                fragmentTransaction.commit();
                toAccount();
            }
        });

    }

    private void toShop() {
        shopTxt.setTextColor(getResources().getColor(R.color.teal_200));
        imgCircle.setImageResource(R.drawable.ic_circle_selected);
        navHome.setImageResource(R.drawable.ic_home);
        navFav.setImageResource(R.drawable.ic_fav);
        navAccount.setImageResource(R.drawable.ic_person);
    }

    private void toHome() {
        navHome.setImageResource(R.drawable.ic_home_selected);
        shopTxt.setTextColor(getResources().getColor(R.color.icons));
        imgCircle.setImageResource(R.drawable.ic_circle);
        navFav.setImageResource(R.drawable.ic_fav);
        navAccount.setImageResource(R.drawable.ic_person);
    }

    private void toFav() {
        navFav.setImageResource(R.drawable.ic_fav_selected);
        navHome.setImageResource(R.drawable.ic_home);
        shopTxt.setTextColor(getResources().getColor(R.color.icons));
        imgCircle.setImageResource(R.drawable.ic_circle);
        navAccount.setImageResource(R.drawable.ic_person);
    }

    private void toAccount() {
        navAccount.setImageResource(R.drawable.ic_person_selected);
        navFav.setImageResource(R.drawable.ic_fav);
        navHome.setImageResource(R.drawable.ic_home);
        shopTxt.setTextColor(getResources().getColor(R.color.icons));
        imgCircle.setImageResource(R.drawable.ic_circle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (order.getProducts().size() < 1) {
            quantityProducts.setVisibility(View.GONE);
            quantityProductsFrag.setVisibility(View.GONE);
        } else {
            quantityProducts.setVisibility(View.VISIBLE);
            quantityProductsFrag.setVisibility(View.VISIBLE);
            quantityProducts.setText(String.valueOf(order.getProducts().size()));
            quantityProductsFrag.setText(String.valueOf(order.getProducts().size()));
        }
    }
}