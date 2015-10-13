package com.holmusk.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by gmsdvt on 10/12/15.
 */
public class GoogleSearchUtil {
    private static final OkHttpClient client = new OkHttpClient();

    public interface GoogleQueryReturnedCallback {
        public <T> void onQueryReturned(int pos, String url);
    }

    public static void searchImageWithQuery(String query, final int position, final GoogleQueryReturnedCallback callback) throws Exception {
        String url = Constants.GOOGLE_IMAGE_URL+query;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    JSONObject data = new JSONObject(jsonData);
                    String url = data.getJSONObject("responseData").getJSONArray("results").getJSONObject(0).getString("url");
                    callback.onQueryReturned(position, url);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }


}
