package com.holmusk.restapi;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.holmusk.model.food.Food;
import com.holmusk.utils.Constants;

import java.util.List;

import io.realm.RealmObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by gmsdvt on 10/11/15.
 */
public class RestHandler {
    private static RestHandler newInstance = new RestHandler();

    private RestEndpoints restClient;
    Gson gson = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    public static RestHandler getInstance() {
        return newInstance;
    }
    private RestHandler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        restClient = retrofit.create(RestEndpoints.class);
    }


    public void searchFoodWithCallback(String query, Callback<List<Food>> callback){
        Call<List<Food>> call = restClient.searchFood(query);
        call.enqueue(callback);
    }


}
