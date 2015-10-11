package com.holmusk.eatright.ui.presenters;

import android.content.Context;
import android.util.Log;

import com.holmusk.eatright.dao.CaloriePerDayDao;
import com.holmusk.eatright.dao.FoodDao;
import com.holmusk.eatright.dao.MyFoodDao;
import com.holmusk.eatright.models.BaseFood;
import com.holmusk.eatright.models.CaloriePerDay;
import com.holmusk.eatright.ui.views.FoodDetailsView;

import org.joda.time.DateTime;

/**
 * Created by kai on 2015-10-08.
 */
public class FoodDetailsPresenter implements Presenter {

    private static final String TAG = FoodDetailsPresenter.class.getSimpleName();

    private FoodDetailsView mFoodDetailsView;
    private FoodDao mFoodDao;
    private MyFoodDao mMyFoodDao;
    private CaloriePerDayDao mCalorieDao;

    public FoodDetailsPresenter(Context context) {
        mFoodDao = new FoodDao(context);
        mMyFoodDao = new MyFoodDao(context);
        mCalorieDao = new CaloriePerDayDao(context);
    }

    public void setView(FoodDetailsView view) {
        mFoodDetailsView = view;
    }

    public void getFoodById(String id, boolean isMyFood) {
        DateTime beforeQuery = DateTime.now();
        BaseFood food;
        if (!isMyFood) {
            food = mFoodDao.searchFoodById(id);
        } else {
            food = mMyFoodDao.getFoodByName(id);
        }
        Log.d(TAG, "Query took: " + (DateTime.now().getMillis() - beforeQuery.getMillis()) + " milliseconds");
        mFoodDetailsView.renderFoodDetails(food);
    }

    public void addCalorie(float calorie) {
        DateTime now = DateTime.now();
        int day = now.getDayOfWeek();
        CaloriePerDay caloriePerDay = mCalorieDao.getDay(day);
        float existingCalories;

        if (caloriePerDay == null) {
            caloriePerDay = new CaloriePerDay();
            caloriePerDay.setDayOfWeek(day);
            existingCalories = calorie;
        } else {
            existingCalories = caloriePerDay.getCalories();
            existingCalories += calorie;
        }

        mCalorieDao.saveDay(caloriePerDay, existingCalories);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
