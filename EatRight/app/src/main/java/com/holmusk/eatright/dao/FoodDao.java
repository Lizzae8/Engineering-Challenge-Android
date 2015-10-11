package com.holmusk.eatright.dao;

import android.content.Context;
import android.util.Log;

import com.holmusk.eatright.models.Food;

import org.joda.time.DateTime;

import java.util.List;

import io.realm.Realm;

/**
 * Created by kai on 2015-10-07.
 */
public class FoodDao {

    private static final String TAG = FoodDao.class.getSimpleName();
    private Realm mRealm;

    public FoodDao(Context context){
        mRealm = DaoFactory.getInstance(context);
    }

    public void saveAllFood(List<Food> foods){
        DateTime now = DateTime.now();
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(foods);
        mRealm.commitTransaction();
        Log.d(TAG, "Saving to database took: " + (DateTime.now().getMillis() - now.getMillis())
                + " milliseconds");

    }

    public Food searchFoodById(String id){
        DateTime now = DateTime.now();
        Food food = mRealm.where(Food.class).equalTo("id", id).findFirst();
        Log.d(TAG, "Retrieving from database took: " + (DateTime.now().getMillis() - now.getMillis())
                + " milliseconds");
        return food;
    }
}
