package com.holmusk.model.dao;

import com.holmusk.model.food.Food;

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
        RealmResults<Food> results = realm.where(Food.class).findAll();
        return results;
    }

    @Override
    public RealmResults<Food> findFoodById(String query) {
        RealmResults<Food> results = realm.where(Food.class).equalTo("Id", query).findAll();
        return results;
    }

    @Override
    public Food findFoodByUid(String id) {
        Food result = realm.where(Food.class).equalTo("uid", id).findFirst();
        return null;
    }

    @Override
    public boolean addOrUpdateFoodItem(Food foodItem) {
        RealmResults<Food> recentResults = findFoodById(foodItem.getId());
        if(recentResults!=null && recentResults.size()>0){
            foodItem.setId(recentResults.get(0).getId());//Update existing record
        }else{//create new record if _id not existed
            foodItem.setUid((UUID.randomUUID().toString()));
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foodItem);
        realm.commitTransaction();

        return true;
    }

    @Override
    public boolean addOrUpdateFoodList(List<Food> foodList) {
        for (Food item:foodList){
            addOrUpdateFoodItem(item);
        }


        return true;
    }
}
