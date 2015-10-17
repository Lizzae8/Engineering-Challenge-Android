package com.holmusk.model.dao;

import android.util.Log;

import com.holmusk.model.food.Food;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gmsdvt on 10/14/15.
 */
public class FoodDAOImpl implements FoodDAO {
    private Realm realm;
    private static final String TAG = FoodDAOImpl.class.getSimpleName();

    protected FoodDAOImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public RealmResults<Food> getAllFood() {
        long startTime = new Date().getTime();
        RealmResults<Food> results = realm.where(Food.class).findAll();
        Log.e("Time taken", "for getAllFood: " + (new Date().getTime() - startTime) + " milliseconds");

        return results;
    }

    @Override
    public RealmResults<Food> findFoodById(String query) {
        long startTime = new Date().getTime();
        RealmResults<Food> results = realm.where(Food.class).equalTo("Id", query).findAll();
        Log.e("Time taken","for findFoodById: "+(new Date().getTime()-startTime)+" milliseconds");

        return results;
    }

    @Override
    public Food findFoodByUid(String id) {
        long startTime = new Date().getTime();
        Food result = realm.where(Food.class).equalTo("uid", id).findFirst();
        Log.e("Time taken","for findFoodByUid: "+(new Date().getTime()-startTime)+" milliseconds");

        return null;
    }

    @Override
    public boolean addOrUpdateFoodItem(Food foodItem) {
        long startTime = new Date().getTime();
        RealmResults<Food> recentResults = findFoodById(foodItem.getId());
        if(recentResults!=null && recentResults.size()>0){
            foodItem.setId(recentResults.get(0).getId());//Update existing record
        }else{//create new record if _id not existed
            foodItem.setUid((UUID.randomUUID().toString()));
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foodItem);
        realm.commitTransaction();
        Log.e("Time taken", "for addOrUpdateFoodItem: " + (new Date().getTime() - startTime) + " milliseconds");

        return true;
    }

    @Override
    public boolean addOrUpdateFoodList(List<Food> foodList) {
        long startTime = new Date().getTime();
        for (Food item:foodList){
            addOrUpdateFoodItem(item);
        }
        Log.e("Time taken","for addOrUpdateFoodList: "+(new Date().getTime()-startTime)+" milliseconds");

        return true;
    }
}
