package com.example.challengeapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challengeapplication.Activities.MainActivity;
import com.example.challengeapplication.Models.Order;
import com.example.challengeapplication.Models.Product;
import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerOrderAdapter extends RecyclerView.Adapter<RecyclerOrderAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> productList;
    //RequestOptions option;
    private Order order;
    private Activity activity;
    private TextView subtotal;
    private TextView discount;
    private TextView total;
    RecyclerView recyclerOrder;


    public RecyclerOrderAdapter(Context mContext, Order order) {
        this.mContext = mContext;
        // Request option for Glide
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        this.order = order;
        this.productList = this.order.getProducts();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_order, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.plusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList.get(viewHolder.getAdapterPosition()).setQuantity(productList.get(viewHolder.getAdapterPosition()).getQuantity() + 1);
                productList.get(viewHolder.getAdapterPosition()).calculateSubtotal();
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
                order.calculateSubtotal();
                order.calculateDiscount();
                order.calculateTotal();
                subtotal.setText(order.getSubtotal().toString());
                discount.setText(order.getDiscount().toString());
                total.setText(order.getTotal().toString());
                viewHolder.quantity.setText(String.valueOf(productList.get(viewHolder.getAdapterPosition()).getQuantity()));
                viewHolder.total.setText(productList.get(viewHolder.getAdapterPosition()).getSubtotal().toString());
                for (Product product : productList) {
                    if (product.getDiscount().compareTo(new BigDecimal("0")) == 0)
                        product.getDiscountTxt().setVisibility(View.GONE);
                    else {
                        product.getDiscountTxt().setText(product.getDiscount().toString());
                        product.getDiscountTxt().setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productList.get(viewHolder.getAdapterPosition()).getQuantity() > 0) {
                    if (productList.get(viewHolder.getAdapterPosition()).getQuantity() == 1) {
                        if (productList.size() == 1) {
                            AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                            adb.setTitle("Confirmation");
                            adb.setMessage("Are you sure to cancel this order ?");
                            adb.setNegativeButton("No", null);
                            adb.setPositiveButton("Yes",
                                    new AlertDialog.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            productList.clear();
                                            newCmd();
                                        }
                                    }).show();
                            return;
                        }
                        // confirmation
                        AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                        adb.setTitle("Confirmation");
                        adb.setMessage("Are you sure to remove this product ");
                        adb.setNegativeButton("No", null);
                        adb.setPositiveButton("Yes",
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        productList.remove(viewHolder.getAdapterPosition());
                                        RecyclerOrderAdapter.this.notifyDataSetChanged();
                                    }
                                }).show();
                        return;
                    }
                    productList.get(viewHolder.getAdapterPosition()).setQuantity(productList.get(viewHolder.getAdapterPosition()).getQuantity() - 1);
                    productList.get(viewHolder.getAdapterPosition()).calculateSubtotal();
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
                    order.calculateSubtotal();
                    order.calculateDiscount();
                    order.calculateTotal();
                    subtotal.setText(order.getSubtotal().toString());
                    discount.setText(order.getDiscount().toString());
                    total.setText(order.getTotal().toString());
                    viewHolder.quantity.setText(String.valueOf(productList.get(viewHolder.getAdapterPosition()).getQuantity()));
                    viewHolder.total.setText(productList.get(viewHolder.getAdapterPosition()).getSubtotal().toString());
                    for (Product product : productList) {
                        if (product.getDiscount().compareTo(new BigDecimal("0")) == 0)
                            product.getDiscountTxt().setVisibility(View.GONE);
                        else {
                            product.getDiscountTxt().setText(product.getDiscount().toString());
                            product.getDiscountTxt().setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        return viewHolder;
    }

    private void newCmd() {
        StaticVariable.order = new Order();
        StaticVariable.product = new Product();
        StaticVariable.categories = new ArrayList<>();
        StaticVariable.products = new ArrayList<>();
        Intent intent = new Intent(activity, MainActivity.class);
        this.activity.finish();
        this.activity.startActivity(intent);
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(list.get(position).isShowMenu()){
//            return SHOW_MENU;
//        }else{
//            return HIDE_MENU;
//        }
//    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.nameProd.setText(productList.get(position).getName());
        holder.total.setText(productList.get(position).getSubtotal().toString());
        holder.quantity.setText(String.valueOf(productList.get(position).getQuantity()));
        //holder.image.setImageResource(foodList.get(position).getImg());
        holder.image.setImageResource(productList.get(position).getImg());
        holder.discount.setText(productList.get(position).getDiscount().toString());
        holder.discount.setPaintFlags(holder.discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (productList.get(position).getDiscount().compareTo(new BigDecimal("0")) == 0)
            holder.discount.setVisibility(View.GONE);
        else
            holder.discount.setVisibility(View.VISIBLE);

        productList.get(position).setDiscountTxt(holder.discount);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout contentText;
        TextView nameProd, discount, quantity, total;
        ImageView image;
        RelativeLayout plusQuantity, minus, view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            contentText = itemView.findViewById(R.id.contentText);
            nameProd = itemView.findViewById(R.id.nameProd);
            discount = itemView.findViewById(R.id.discount);
            quantity = itemView.findViewById(R.id.quantity);
            total = itemView.findViewById(R.id.total);
            image = itemView.findViewById(R.id.imgProd);
            plusQuantity = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
        }
    }


    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public RecyclerView getRecyclerOrder() {
        return recyclerOrder;
    }

    public void setRecyclerOrder(RecyclerView recyclerOrder) {
        this.recyclerOrder = recyclerOrder;
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


    public TextView getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(TextView subtotal) {
        this.subtotal = subtotal;
    }

    public TextView getDiscount() {
        return discount;
    }

    public void setDiscount(TextView discount) {
        this.discount = discount;
    }

    public TextView getTotal() {
        return total;
    }

    public void setTotal(TextView total) {
        this.total = total;
    }
}
