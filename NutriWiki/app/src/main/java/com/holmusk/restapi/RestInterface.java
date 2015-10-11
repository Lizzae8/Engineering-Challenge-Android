package com.holmusk.restapi;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gmsdvt on 10/11/15.
 */
public interface RestInterface {
    @GET("/food/search")
    Call<String> search(@Query("q") String query);
}

