package com.holmusk.eatright.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kai on 2015-10-05.
 */
public class RestClient {

    private static final String SERVER_URL = "http://test.holmusk.com/";
    private static RestApi restApi;

    private static void setUpApi() {

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        restApi = retrofit.create(RestApi.class);
    }

    public static RestApi get() {
        if (restApi == null) {
            setUpApi();
        }
        return restApi;
    }
}
