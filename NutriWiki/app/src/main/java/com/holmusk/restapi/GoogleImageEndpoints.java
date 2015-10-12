package com.holmusk.restapi;

import java.util.Map;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by gmsdvt on 10/11/15.
 */
public interface GoogleImageEndpoints {
    @GET("/ajax/services/search/images")
    Call<Response> searchImageWithKeyword(@QueryMap Map<String, String> options);

}

