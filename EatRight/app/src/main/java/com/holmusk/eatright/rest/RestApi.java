package com.holmusk.eatright.rest;

import com.holmusk.eatright.models.Food;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kai on 2015-10-06.
 */
public interface RestApi {

    // Return list of foods to be displayed
    @GET("/food/search")
    Call<List<Food>> searchFood(@Query("q") String query);
}
