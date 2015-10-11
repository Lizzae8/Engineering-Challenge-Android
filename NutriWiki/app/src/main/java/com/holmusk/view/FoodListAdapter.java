package com.holmusk.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nutriwiki.holmusk.com.nutriwiki.R;

/**
 * Created by gmsdvt on 10/10/15.
 */
public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodItemViewHolder>{
    List<FoodItem> foodItems;

    public FoodListAdapter(List<FoodItem> foodItems){
        this.foodItems = foodItems;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_item_layout, viewGroup, false);
        FoodItemViewHolder pvh = new FoodItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FoodItemViewHolder foodItemViewHolder, int position) {
        foodItemViewHolder.foodName.setText(foodItems.get(position).getName());
        foodItemViewHolder.foodCalories.setText(foodItems.get(position).getCalories()+" kcal");
        foodItemViewHolder.foodPortion.setText(foodItems.get(position).getPortion());
        //foodItemViewHolder.foodPhoto.setImageResource(foodItems.get(position).photoId);

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