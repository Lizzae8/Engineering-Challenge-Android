package com.holmusk.view;

import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.holmusk.model.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

import nutriwiki.holmusk.com.nutriwiki.R;

/**
 * Created by gmsdvt on 10/10/15.
 */
public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodItemViewHolder> implements View.OnClickListener{
    List<Food> foodItems;
    private OnItemClickListener onItemClickListener;

    public FoodListAdapter(List<Food> foodItems){
        this.foodItems = foodItems;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_item_layout, viewGroup, false);
        FoodItemViewHolder pvh = new FoodItemViewHolder(v);
        v.setOnClickListener(this);
        return pvh;
    }

    @Override
    public void onBindViewHolder( FoodItemViewHolder holder,  final int position) {
        holder.foodName.setText(foodItems.get(position).getName());
        holder.foodCalories.setText(foodItems.get(position).getPortions().get(0).getNutrients().getImportant().getCalories().getValue()+" " +foodItems.get(position).getPortions().get(0).getNutrients().getImportant().getCalories().getUnit());
        holder.foodPortion.setText(foodItems.get(position).getPortions().get(0).getName());
        //holder.foodPhoto.setImageResource(foodItems.get(position).photoId);


        String photoUrl = foodItems.get(position).getPhotoUrl();
        if (photoUrl!=null && !photoUrl.equals(""))
            Picasso.with(holder.foodPhoto.getContext()).load(photoUrl).into(holder.foodPhoto);

        holder.itemView.setTag(foodItems.get(position));
    }
    @UiThread

    public void refreshUI(){

        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

@Override
     public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Food) v.getTag());
                }
            }, 200);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Food food);

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