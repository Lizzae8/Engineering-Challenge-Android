package com.holmusk.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.holmusk.utils.GoogleSearchUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import nutriwiki.holmusk.com.nutriwiki.R;

/**
 * Created by gmsdvt on 10/10/15.
 */
public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodItemViewHolder>{
    List<FoodItem> foodItems;
    Context context;
    public FoodListAdapter(List<FoodItem> foodItems, Context context){
        this.foodItems = foodItems;
        this.context = context;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_item_layout, viewGroup, false);
        FoodItemViewHolder pvh = new FoodItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final FoodItemViewHolder foodItemViewHolder, int position) {
        foodItemViewHolder.foodName.setText(foodItems.get(position).getName());
        foodItemViewHolder.foodCalories.setText(foodItems.get(position).getCalories()+" kcal");
        foodItemViewHolder.foodPortion.setText(foodItems.get(position).getPortion());
        //foodItemViewHolder.foodPhoto.setImageResource(foodItems.get(position).photoId);
        final String[] url = new String[1];
        try {
            GoogleSearchUtil.searchImageWithQuery(foodItems.get(position).getName(), new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    url[0] = (response.body().string());
                    Log.e("Url ", url[0]);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.with(context).load("http://www.aleidgroup.com/bakery/products/burger-bun-seeded.jpg").into(foodItemViewHolder.foodPhoto);

    }

    private  void loadImage(String url){

    }
    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        CardView foodItemView;
        TextView foodName;
        TextView foodCalories;
        TextView foodPortion;
        ImageView foodPhoto;

        FoodItemViewHolder(View itemView) {
            super(itemView);
            foodItemView = (CardView)itemView.findViewById(R.id.food_item);
            foodName = (TextView)itemView.findViewById(R.id.food_name);
            foodCalories = (TextView)itemView.findViewById(R.id.food_calories);
            foodPortion = (TextView)itemView.findViewById(R.id.food_portion);
            foodPhoto = (ImageView)itemView.findViewById(R.id.food_photo);
        }
    }

}