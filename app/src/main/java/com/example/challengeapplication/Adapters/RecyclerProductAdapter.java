package com.example.challengeapplication.Adapters;

import static com.example.challengeapplication.Models.StaticVariable.order;
import static com.example.challengeapplication.Models.StaticVariable.quantityProducts;
import static com.example.challengeapplication.Models.StaticVariable.quantityProductsFrag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.challengeapplication.Activities.ProductActivity;
import com.example.challengeapplication.Models.Order;
import com.example.challengeapplication.Models.Product;
import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

import java.util.List;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> productList;
    //RequestOptions option;
    private String type;
    private Order order;
    private Activity activity;


    public RecyclerProductAdapter(Context mContext, List<Product> zoneList, String type, Order order) {
        this.mContext = mContext;
        this.productList = zoneList;
        this.order = order;
        // Request option for Glide
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_product, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);


        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticVariable.product = productList.get(viewHolder.getAdapterPosition());
                mContext.startActivity(new Intent(mContext, ProductActivity.class));
            }
        });
        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!productList.get(viewHolder.getAdapterPosition()).isInOrder()) {
                    //if (foodList.get(viewHolder.getAdapterPosition()).getQuantity() > 0) {
                    viewHolder.icCart.setImageResource(R.drawable.ic_shopping_white);
                    viewHolder.addToCart.setBackgroundResource(R.drawable.rectangle_blue);
                    //viewHolder.addToCmd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_remove_shopping, 0, 0, 0);
                    productList.get(viewHolder.getAdapterPosition()).setQuantity(1);
                    productList.get(viewHolder.getAdapterPosition()).setInOrder(true);//viewHolder.inOrder = true;
                    order.getProducts().add(productList.get(viewHolder.getAdapterPosition()));
//                    if (foodList.get(viewHolder.getAdapterPosition()).getQuantity() == 0) {
//                        foodList.get(viewHolder.getAdapterPosition()).setQuantity(foodList.get(viewHolder.getAdapterPosition()).getQuantity() + 1);
//                        foodList.get(viewHolder.getAdapterPosition()).calculatePriceTotal();
//                    }
                    //} else{
                    //Toast.makeText(mContext, "size: " + order.getFoods().size(), Toast.LENGTH_SHORT).show();

                } else {
                    viewHolder.icCart.setImageResource(R.drawable.ic_shopping);
                    viewHolder.addToCart.setBackgroundResource(R.drawable.rectangle_border_white);
                    productList.get(viewHolder.getAdapterPosition()).setInOrder(false);
                    //viewHolder.inOrder = false;
                    productList.get(viewHolder.getAdapterPosition()).setQuantity(0);
                    order.getProducts().remove(productList.get(viewHolder.getAdapterPosition()));
                    //Toast.makeText(mContext, "size: " + order.getFoods().size(), Toast.LENGTH_SHORT).show();
                }
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
        });

//        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (type.equals("magazin"))
//                    return;
//                Intent i = new Intent(mContext, MagazinActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("magazin", clientList.get(viewHolder.getAdapterPosition()));
//                i.putExtras(bundle);
//                mContext.startActivity(i);
//            }
//        });
//        viewHolder.view_container.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                return false;
//            }
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.image.setImageResource(productList.get(position).getImg());
        holder.categoryName.setText(productList.get(position).getCategory().getName());
        holder.titleProd.setText(productList.get(position).getName());
        holder.priceProd.setText("£" + productList.get(position).getPriceUnity().toString());
        Product selfFood = productList.get(position);
//        for (Product food : StaticVariable.foods)
//            if (food.getId() == selfFood.getId())
//                selfFood = food;

        if (!selfFood.isInOrder()) {
            holder.icCart.setImageResource(R.drawable.ic_shopping);
            holder.addToCart.setBackgroundResource(R.drawable.rectangle_border_white);
        } else {
            holder.icCart.setImageResource(R.drawable.ic_shopping_white);
            holder.addToCart.setBackgroundResource(R.drawable.rectangle_blue);
        }

        //holder.image.setImageResource(foodList.get(position).getImg());

        // Load Image from the internet and set it into Imageview using Glide
        //Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.layout_thumbnail);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleProd, categoryName, priceProd;
        ImageView image, icCart;
        RelativeLayout view_container, addToCart;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            titleProd = itemView.findViewById(R.id.titleProd);
            priceProd = itemView.findViewById(R.id.priceProd);
            image = itemView.findViewById(R.id.imgPlat);
            categoryName = itemView.findViewById(R.id.categoryName);
            icCart = itemView.findViewById(R.id.ic_cart);
            addToCart = itemView.findViewById(R.id.add_to_cart);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    //    @Override
//    public int getViewTypeCount() {
//        return getCount();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
}
