package com.holmusk.eatright.network;

import com.holmusk.eatright.models.Food;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kai on 2015-10-06.
 */
public interface RestApi {

    // Return list of trips to be displayed in the front screen
    @GET("/food/search")
    public void searchFood(@Query("q") String query, Callback<List<Food>> callback);
}
