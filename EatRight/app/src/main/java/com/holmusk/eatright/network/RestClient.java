package com.holmusk.eatright.network;

import retrofit.Retrofit;

/**
 * Created by kai on 2015-10-05.
 */
public class RestClient {

    private static final String SERVER_URL = "http://test.holmusk.com/";
    private static RestApi restApi;

    private static void setUpApi() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .build();
        restApi = adapter.create(RestApi.class);
    }

    public static RestApi get() {
        if (restApi == null) {
            setUpApi();
        }
        return restApi;
    }
}
