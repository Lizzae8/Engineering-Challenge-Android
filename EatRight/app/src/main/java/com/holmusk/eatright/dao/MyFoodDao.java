package com.holmusk.eatright.dao;

import android.content.Context;

import com.holmusk.eatright.models.MyFood;

import java.util.List;

import io.realm.Realm;

/**
 * Created by kai on 2015-10-09.
 */
public class MyFoodDao {

    private Realm mRealm;

    public MyFoodDao(Context context) {
        mRealm = DaoFactory.getInstance(context);
    }

    public void saveFood(MyFood myFood) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(myFood);
        mRealm.commitTransaction();
    }

    public List<MyFood> getAllMyFood() {
        return mRealm.where(MyFood.class).findAll();
    }

    public MyFood getFoodByName(String name) {
        return mRealm.where(MyFood.class).equalTo("name", name).findFirst();
    }
}
