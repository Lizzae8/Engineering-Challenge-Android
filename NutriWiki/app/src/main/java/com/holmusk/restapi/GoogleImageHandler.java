package com.holmusk.restapi;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.holmusk.utils.Constants;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by gmsdvt on 10/11/15.
 */
public class GoogleImageHandler {
    private static GoogleImageHandler newInstance = new GoogleImageHandler();

    private GoogleImageEndpoints restClient;

//    Gson gson = new GsonBuilder()
//                    .registerTypeAdapter(Content.class, new MyDeserializer())
//                    .create();

    public static GoogleImageHandler getInstance() {
        return newInstance;
    }

    private GoogleImageHandler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GOOGLE_IMAGE_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(GoogleImageEndpoints.class);
    }


    public void searchImageWithCallback(Map<String,String> query, Callback<Response> callback){

        Call<Response> call = restClient.searchImageWithKeyword(query);
        call.enqueue(callback);
    }


//    class MyDeserializer<T> implements JsonDeserializer<T>
//    {
//        @Override
//        public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
//                throws JsonParseException
//        {
//            String content = je.getAsJsonObject().getAsJsonObject("responseData").getAsJsonArray("results").get(0).getAsJsonObject().get("url").getAsString();
//            return new Gson().fromJson(content, type);
//
//        }
//    }
//
    public class Content
    {
        @SerializedName("responseData")
        public String url;


    }


}
