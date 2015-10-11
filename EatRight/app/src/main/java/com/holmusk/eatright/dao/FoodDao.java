package com.holmusk.eatright.dao;

import android.content.Context;

import com.holmusk.eatright.models.Food;

import java.util.List;

import io.realm.Realm;

/**
 * Created by kai on 2015-10-07.
 */
public class FoodDao {

    private Realm mRealm;

    public FoodDao(Context context){
        mRealm = DaoFactory.getInstance(context);
    }

    public void saveAllFood(List<Food> foods){
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(foods);
        mRealm.commitTransaction();
    }

    public Food searchFoodById(String id){
        Food food = mRealm.where(Food.class).equalTo("id", id).findFirst();
        return food;
    }
}
