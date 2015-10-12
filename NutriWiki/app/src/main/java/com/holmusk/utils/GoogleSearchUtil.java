package com.holmusk.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by gmsdvt on 10/12/15.
 */
public class GoogleSearchUtil {
    private static final OkHttpClient client = new OkHttpClient();

    public static void searchImageWithQuery(String query, com.squareup.okhttp.Callback callback) throws Exception {
        String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&start=1&imgsz=medium&q="+query;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
