package com.example.challengeapplication.Fragments;

import static com.example.challengeapplication.Models.StaticVariable.categories;
import static com.example.challengeapplication.Models.StaticVariable.order;
import static com.example.challengeapplication.Models.StaticVariable.products;
import static com.example.challengeapplication.Models.StaticVariable.quantityProducts;
import static com.example.challengeapplication.Models.StaticVariable.quantityProductsFrag;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeapplication.Activities.MainActivity;
import com.example.challengeapplication.Activities.OrderActivity;
import com.example.challengeapplication.Adapters.RecyclerCategoriesAdapter;
import com.example.challengeapplication.Adapters.RecyclerProductAdapter;
import com.example.challengeapplication.Models.Category;
import com.example.challengeapplication.Models.Product;
import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RelativeLayout navCart;
    TextView nbProd;
    RecyclerView listCategories;
    RecyclerView listProducts;
    RecyclerCategoriesAdapter categoriesAdapter;
    RecyclerProductAdapter recyclerProductAdapter;

    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        navCart = view.findViewById(R.id.nav_cart);
        nbProd = view.findViewById(R.id.nb_prod);
        listCategories = view.findViewById(R.id.listCategories);
        listProducts = view.findViewById(R.id.listProducts);

        quantityProductsFrag = nbProd;
        navCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StaticVariable.order.getProducts().size() == 0) {
                    Toast.makeText(getContext(), "There is no products for this order !", Toast.LENGTH_SHORT).show();
                    return;
                }
                getContext().startActivity(new Intent(getContext(), OrderActivity.class));
            }
        });


        Category category = new Category(0, "All");
        category.setSelected(true);
        categories.add(category);
        categories.add(new Category(1, "Indoor"));
        categories.add(new Category(2, "Outdoor"));
        categories.add(new Category(3, "Big plantes"));
        products.add(new Product(1, "Butter", categories.get(1), R.drawable.img_butter, "butter, a yellow-to-white solid emulsion of fat globules, water, and inorganic salts produced by churning the cream from cows’ milk. Butter has long been used as a spread and as a cooking fat. It is an important edible fat in northern Europe, North America, and other places where cattle are the primary dairy animals. In all, about a third of the world’s milk production is devoted to making butter.", new BigDecimal("0.80")));
        products.add(new Product(2, "Milk", categories.get(2), R.drawable.img_milk, "milk, liquid secreted by the mammary glands of female mammals to nourish their young for a period beginning immediately after birth. The milk of domesticated animals is also an important food source for humans, either as a fresh fluid or processed into a number of dairy products such as butter and cheese. ", new BigDecimal("1.15")));
        products.add(new Product(3, "Bread", categories.get(1), R.drawable.img_bread, "Bread is a staple food prepared from a dough of flour (usually wheat) and water, usually by baking. Throughout recorded history and around the world, it has been an important part of many cultures' diet. It is one of the oldest human-made foods, having been of significance since the dawn of agriculture, and plays an essential role in both religious rituals and secular culture.", new BigDecimal("1.00")));

        setupRecyclerCategoriesView();
        setupRecyclerFoodsView();
        return view;
    }

    public void setupRecyclerCategoriesView() {
        categoriesAdapter = new RecyclerCategoriesAdapter(getContext(), categories, "");
        listCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        listCategories.setAdapter(categoriesAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }

    }

    public void setupRecyclerFoodsView() {
        recyclerProductAdapter = new RecyclerProductAdapter(getContext(), products, "", order);
        //recyclerFood.setLayoutManager(new LinearLayoutManager(this));
        listProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        listProducts.setAdapter(recyclerProductAdapter);
        categoriesAdapter.setRecyclerProductAdapter(recyclerProductAdapter);
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            //Do some stuff
//            recyclerFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        }
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            //Do some stuff
//
//        }
        listProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        setupRecyclerFoodsView();
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