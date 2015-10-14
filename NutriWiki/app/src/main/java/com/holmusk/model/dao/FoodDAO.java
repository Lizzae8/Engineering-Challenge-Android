package com.holmusk.model.dao;

import com.holmusk.model.food.Food;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by gmsdvt on 10/14/15.
 */

public interface FoodDAO {
    RealmResults<Food> getAllFood();
    RealmResults<Food> findFoodById(String id);
    Food findFoodByUid(String id);

    boolean addOrUpdateFoodItem(Food foodItem);
    boolean addOrUpdateFoodList(List<Food> foodList);
}