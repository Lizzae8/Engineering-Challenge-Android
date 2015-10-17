package com.holmusk.view.components.scrollableviewpager;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.holmusk.model.food.Food;
import com.holmusk.restapi.RestHandler;
import com.holmusk.utils.GoogleSearchUtil;
import com.holmusk.view.components.FoodListAdapter;

import java.util.ArrayList;
import java.util.List;

import nutriwiki.holmusk.com.nutriwiki.R;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RecyclerViewFragment extends BaseFragment implements GoogleSearchUtil.GoogleQueryReturnedCallback {

    public static final String TAG = "tag.RecyclerViewFragment";
     List<Food> foodItems;
    public static RecyclerViewFragment newInstance(int color) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLOR, color);

        final RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    FoodListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {


        final View view = inflater.inflate(R.layout.fragment_recycler_view, parent, false);

        mRecyclerView = findView(view, R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        foodItems = new ArrayList<Food>();
        adapter = new FoodListAdapter(foodItems, getActivity());
        mRecyclerView.setAdapter(adapter);

        //Populate the list with sample items
        populateMockupData();
        return view;
    }

    private void populateMockupData(){
        RestHandler.getInstance().searchFoodWithCallback("Burger", new Callback<List<Food>>() {
            @Override
            public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                List<Food> foodList = response.body();
                if (foodList!=null && foodList.size()>0){
                    Food newFood = foodList.get(0);
                    foodItems.add(newFood);

                    try {
                        GoogleSearchUtil.searchImageWithQuery(newFood.getName(), foodItems.size() - 1, RecyclerViewFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        RestHandler.getInstance().searchFoodWithCallback("mee", new Callback<List<Food>>() {
            @Override
            public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                List<Food> foodList = response.body();
                if (foodList!=null && foodList.size()>0){
                    Food newFood = foodList.get(0);
                    foodItems.add(newFood);

                    try {
                        GoogleSearchUtil.searchImageWithQuery(newFood.getName(), foodItems.size() - 1, RecyclerViewFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        RestHandler.getInstance().searchFoodWithCallback("fish head", new Callback<List<Food>>() {
            @Override
            public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                List<Food> foodList = response.body();
                if (foodList!=null && foodList.size()>0){
                    Food newFood = foodList.get(0);
                    foodItems.add(newFood);

                    try {
                        GoogleSearchUtil.searchImageWithQuery(newFood.getName(), foodItems.size() - 1, RecyclerViewFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        RestHandler.getInstance().searchFoodWithCallback("coffee", new Callback<List<Food>>() {
            @Override
            public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                List<Food> foodList = response.body();
                if (foodList!=null && foodList.size()>0){
                    Food newFood = foodList.get(0);
                    foodItems.add(newFood);

                    try {
                        GoogleSearchUtil.searchImageWithQuery(newFood.getName(), foodItems.size() - 1, RecyclerViewFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        RestHandler.getInstance().searchFoodWithCallback("banana", new Callback<List<Food>>() {
            @Override
            public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                List<Food> foodList = response.body();
                if (foodList!=null && foodList.size()>0){
                    Food newFood = foodList.get(0);
                    foodItems.add(newFood);

                    try {
                        GoogleSearchUtil.searchImageWithQuery(newFood.getName(), foodItems.size() - 1, RecyclerViewFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        RestHandler.getInstance().searchFoodWithCallback("yong tau foo", new Callback<List<Food>>() {
            @Override
            public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                List<Food> foodList = response.body();
                if (foodList!=null && foodList.size()>0){
                    Food newFood = foodList.get(0);
                    foodItems.add(newFood);

                    try {
                        GoogleSearchUtil.searchImageWithQuery(newFood.getName(), foodItems.size() - 1, RecyclerViewFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    @Override
    public CharSequence getTitle(Resources r) {
        return "TODAY";
    }

    @Override
    public String getSelfTag() {
        return TAG;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return mRecyclerView != null && mRecyclerView.canScrollVertically(direction);
    }

    @Override
    public <T> void onQueryReturned(int pos, String url) {
        foodItems.get(pos).setPhotoUrl(url);
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();

            }
        });
    }
}
