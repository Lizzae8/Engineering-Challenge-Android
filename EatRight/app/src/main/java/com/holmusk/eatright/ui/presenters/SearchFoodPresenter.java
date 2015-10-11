package com.holmusk.eatright.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.holmusk.eatright.dao.FoodDao;
import com.holmusk.eatright.models.Food;
import com.holmusk.eatright.rest.RestClient;
import com.holmusk.eatright.ui.activities.FoodDetailsActivity;
import com.holmusk.eatright.ui.views.SearchFoodView;

import org.joda.time.DateTime;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kai on 2015-10-07.
 */
public class SearchFoodPresenter implements Presenter, Callback<List<Food>> {

    private static final String TAG = SearchFoodPresenter.class.getSimpleName();

    // View that is responsible to render data on screen
    private SearchFoodView mSearchFoodView;

    // Instance of network request
    private  Call<List<Food>> mCall;

    // Keep track of time when query is made to log how long it took to retrieve data
    private DateTime mQueryTime;

    private FoodDao mFoodDao;

    public SearchFoodPresenter(Context context) {
        mFoodDao = new FoodDao(context);
    }

    public void searchFood(String query) {
        mSearchFoodView.showLoading();
        // Cancel previous query in case it is still running and search a new keyword
        if (mCall != null) {
            // AsyncTask wrapper to cancel Retrofit async call
            new CancelTask().execute(mCall);
        }
        mQueryTime = DateTime.now();
        mCall = RestClient.get().searchFood(query);
        mCall.enqueue(this);
    }

    public void setView(@NonNull SearchFoodView view) {
        mSearchFoodView = view;
    }

    public void openFoodDetails(Food food, Context context) {
        Intent intent = new Intent(context, FoodDetailsActivity.class);
        intent.putExtra(FoodDetailsActivity.INTENT_EXTRA_FOOD_ID, food.getId());
        context.startActivity(intent);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
        DateTime doneTime = DateTime.now();
        Log.d(TAG, "Query took: " + (doneTime.getMillis() - mQueryTime.getMillis()) + " milliseconds");
        mSearchFoodView.renderFoodList(response.body());
        mSearchFoodView.hideLoading();
        mFoodDao.saveAllFood(response.body());
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d(TAG, t.getLocalizedMessage());
    }


    // AsyncTask wrapper used to cancel async call from Retrofit due to a bug
    // https://github.com/square/okhttp/issues/1592
    private class CancelTask extends AsyncTask< Call, Void, Void > {
        @Override
        protected Void doInBackground(Call...params) {
            Call call = params[0];
            call.cancel();
            return null;
        }
    }
}
