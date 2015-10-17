package com.holmusk.restapi;

import com.holmusk.utils.Constants;

import org.json.JSONObject;

import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by gmsdvt on 10/11/15.
 */
public class GoogleImageHandler {
    private static GoogleImageHandler newInstance = new GoogleImageHandler();

    private GoogleImageEndpoints restClient;

    public static GoogleImageHandler getInstance() {
        return newInstance;
    }

    private GoogleImageHandler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GOOGLE_AJAX_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(GoogleImageEndpoints.class);
    }


    public void searchImageWithCallback(Map<String,String> query, Callback<JSONObject> callback){
        Call<JSONObject> call = restClient.searchImageWithKeyword(query);
        call.enqueue(callback);
    }

}
