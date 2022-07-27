package com.example.challengeapplication.Adapters;

import static com.example.challengeapplication.Models.StaticVariable.products;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.challengeapplication.Models.Category;
import com.example.challengeapplication.Models.Product;
import com.example.challengeapplication.Models.StaticVariable;
import com.example.challengeapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerCategoriesAdapter extends RecyclerView.Adapter<RecyclerCategoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Category> categoryList;
    //RequestOptions option;
    private String type;
    private Activity activity;
    private RecyclerProductAdapter recyclerProductAdapter;


    public RecyclerCategoriesAdapter(Context mContext, List<Category> zoneList, String type) {
        this.mContext = mContext;
        this.categoryList = zoneList;
        // Request option for Glide
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_category, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (type.equals("magazin"))
////                    return;
////                Intent i = new Intent(mContext, MagazinActivity.class);
////                Bundle bundle = new Bundle();
////                bundle.putSerializable("magazin", clientList.get(viewHolder.getAdapterPosition()));
////                i.putExtras(bundle);
////                mContext.startActivity(i);
                if (!categoryList.get(viewHolder.getAdapterPosition()).isSelected()) {
                    viewHolder.line.setVisibility(View.VISIBLE);
                    categoryList.get(viewHolder.getAdapterPosition()).setSelected(true);
                    //StaticVariable.select = viewHolder.getAdapterPosition();
                    for (int i = 0; i < categoryList.size(); i++)
                        if (i != viewHolder.getAdapterPosition()) {
                            categoryList.get(i).setSelected(false);
                        }
                    recyclerProductAdapter.getProductList().clear();
                    //for (Food food : )
                    ArrayList<Product> listProductsSelected = new ArrayList<>();
                    if (categoryList.get(viewHolder.getAdapterPosition()).getId() == 0)
                        listProductsSelected = products;
                    else
                        for (Product product : products)
                            if (product.getCategory().getId() == categoryList.get(viewHolder.getAdapterPosition()).getId())
                                listProductsSelected.add(product);

                    recyclerProductAdapter.setProductList(listProductsSelected);
                    //recyclerProductAdapter.setProductList(StaticVariable.db.getFoods(categoryList.get(viewHolder.getAdapterPosition())));
                    //System.out.println("size foodList apt : " + recyclerProductAdapter.getFoodList().size());
//                    recyclerFoodAdapter.getFoodList().add(new Food(1, "Checken Burger", categoryList.get(0), R.drawable.burger, "Pizza à pâte fine et croustillante richement garnie de tomates, de jambon, de champignons et de fromage, surgelée"));
//                    recyclerFoodAdapter.getFoodList().add(new Food(1, "Checken Burger", categoryList.get(0), R.drawable.tortilla, "Pizza à pâte fine et croustillante richement garnie de tomates, de jambon, de champignons et de fromage, surgelée"));
//                    recyclerFoodAdapter.getFoodList().add(new Food(1, "Checken Burger", categoryList.get(0), R.drawable.chawarma, "Pizza à pâte fine et croustillante richement garnie de tomates, de jambon, de champignons et de fromage, surgelée"));
                    recyclerProductAdapter.notifyDataSetChanged();
                    notifyDataSetChanged();


                }
            }
        });
        viewHolder.view_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        categoryList.get(position).setSelectedLayout(holder.line);
        holder.nameCategory.setText(categoryList.get(position).getName());
        if (categoryList.get(position).isSelected())
            holder.line.setVisibility(View.VISIBLE);
        else
            holder.line.setVisibility(View.GONE);
    }

    public void swapeItem(int fromPosition, int toPosition) {
        Collections.swap(categoryList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
//    public void setDataset(List<String> dataset) {
//        this.dataset = dataset;
//        notifyDataSetChanged();
//    }
//
//    public void swapItems(int itemAIndex, int itemBIndex) {
//        //make sure to check if dataset is null and if itemA and itemB are valid indexes.
//        String itemA = dataset.get(itemAIndex);
//        String itemB = dataset.get(itemBIndex);
//        dataset.set(itemAIndex, itemB);
//        dataset.set(itemBIndex, ItemA);
//
//        notifyDataSetChanged(); //This will trigger onBindViewHolder method from the adapter.
//    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameCategory;
        LinearLayout view_container, line;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            line = itemView.findViewById(R.id.line);
            nameCategory = itemView.findViewById(R.id.titleCategory);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public RecyclerProductAdapter getRecyclerProductAdapter() {
        return recyclerProductAdapter;
    }

    public void setRecyclerProductAdapter(RecyclerProductAdapter recyclerProductAdapter) {
        this.recyclerProductAdapter = recyclerProductAdapter;
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
