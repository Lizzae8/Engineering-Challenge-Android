package com.holmusk.view.components;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.holmusk.model.food.Calories;
import com.holmusk.model.food.Food;
import com.holmusk.model.food.Portion;
import com.holmusk.utils.Constants;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import nutriwiki.holmusk.com.nutriwiki.R;

/**
 * Created by gmsdvt on 10/10/15.
 */
public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodItemViewHolder> implements View.OnClickListener{
    List<Food> foodItems;
    private OnItemClickListener onItemClickListener;
    Context context;
    public FoodListAdapter(List<Food> foodItems, Context context){
        this.foodItems = foodItems;
        this.context =context;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        FoodItemViewHolder pvh;
        switch (viewType) {
            case Constants.SEARCH_ITEM_TYPE_LOADING:
                View viewLoading = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loading_view, viewGroup, false);
                pvh = new FoodItemViewHolder(viewLoading,viewType);
                return pvh;

            case Constants.SEARCH_ITEM_TYPE_FOOD:
            default:
                View viewFood = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_item_layout, viewGroup, false);
                 pvh = new FoodItemViewHolder(viewFood,viewType);
                viewFood.setOnClickListener(this);
                return pvh;
        }
    }


        @Override
    public void onBindViewHolder( final FoodItemViewHolder holder,  final int position) {
        Food item = foodItems.get(position);
        if (item.getItemType() == Constants.SEARCH_ITEM_TYPE_FOOD){
            String name = foodItems.get(position).getName();
            Double calories = 0.0;

            List<Portion> portionList = foodItems.get(position).getPortions();
            String portionName="";
            String unit = "";
            if (portionList!=null) {
                portionName = portionList.get(0).getName();
                Calories caloriesObj = portionList.get(0).getNutrients().getImportant().getCalories();
                if (caloriesObj!=null) {
                    calories = caloriesObj.getValue();
                    unit = caloriesObj.getUnit();
                }
            }

            holder.foodName.setText(name!=null?name:"-");

            holder.foodCalories.setText((calories>0?calories:"-")+" " +(unit!=null?unit:"-"));
            holder.foodPortion.setText(portionName!=null?portionName:"-");
            //holder.foodPhoto.setImageResource(foodItems.get(position).photoId);

            holder.itemView.setTag(foodItems.get(position));

            String photoUrl = foodItems.get(position).getPhotoUrl();
            if (photoUrl!=null && !photoUrl.equals(Constants.DEFAULT_FOOD_PHOTO))
                Log.e("Loading photo url", photoUrl);
                Picasso.with(holder.foodPhoto.getContext()).load(photoUrl).resize(60, 60).placeholder(R.mipmap.loading).into(holder.foodPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Log.e("Failed to load photo:", holder.foodName.getText().toString());
                        Food item = (Food) holder.itemView.getTag();
                        //Update photo url back to default
                        Realm realm = Realm.getInstance(context);
                        realm.beginTransaction();
                        item.setPhotoUrl(Constants.DEFAULT_FOOD_PHOTO);
                        realm.copyToRealmOrUpdate(item);
                        realm.commitTransaction();


                        Picasso.with(holder.foodPhoto.getContext()).load(item.getPhotoUrl()).resize(60, 60).
                        placeholder(R.mipmap.loading).
                                transform(new CircleTransform()).into(holder.foodPhoto, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Log.e("Load default photo", "failed");
                            }
                        });

                    }
                });
        }


    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
    @Override
    public int getItemViewType(int position) {
        return foodItems.get(position).getItemType();
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
            }, 0);
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

        FoodItemViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == Constants.SEARCH_ITEM_TYPE_FOOD) {
                foodItemView = (CardView) itemView.findViewById(R.id.food_item);
                foodName = (TextView) itemView.findViewById(R.id.food_name);
                foodCalories = (TextView) itemView.findViewById(R.id.food_calories);
                foodPortion = (TextView) itemView.findViewById(R.id.food_portion);
                foodPhoto = (ImageView) itemView.findViewById(R.id.food_photo);
            } else if (viewType == Constants.SEARCH_ITEM_TYPE_LOADING){
                CircleProgressBar circleProgressBar = (CircleProgressBar) itemView.findViewById(R.id.progressBar);
                circleProgressBar.setColorSchemeResources(android.R.color.holo_blue_light,android.R.color.holo_green_light,android.R.color.holo_orange_light,android.R.color.holo_red_light);
            }
        }
    }



}