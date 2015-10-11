package com.holmusk.restapi;

import com.holmusk.model.Food;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gmsdvt on 10/11/15.
 */
public interface RestEndpoints {
    @GET("/food/search")
    Call<List<Food>> searchFood(@Query("q") String query);

}

