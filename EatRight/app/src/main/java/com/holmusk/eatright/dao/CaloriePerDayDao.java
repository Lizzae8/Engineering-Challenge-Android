package com.holmusk.eatright.dao;

import android.content.Context;

import com.holmusk.eatright.models.CaloriePerDay;

import java.util.List;

import io.realm.Realm;

/**
 * Created by kai on 2015-10-08.
 */
public class CaloriePerDayDao {

    private Realm mRealm;

    public CaloriePerDayDao(Context context) {
        mRealm = DaoFactory.getInstance(context);
    }

    public List<CaloriePerDay> getAllDays() {
        return mRealm.where(CaloriePerDay.class).findAll();
    }

    public CaloriePerDay getDay(int dayOfWeek) {
        return mRealm.where(CaloriePerDay.class).equalTo("dayOfWeek", dayOfWeek).findFirst();
    }

    public void clearData() {
        mRealm.beginTransaction();
        List<CaloriePerDay> data = mRealm.where(CaloriePerDay.class).findAll();
        data.clear();
        mRealm.commitTransaction();
    }

    public void saveDay(CaloriePerDay caloriePerDay, float newCalorie) {
        mRealm.beginTransaction();
        caloriePerDay.setCalories(newCalorie);
        mRealm.copyToRealmOrUpdate(caloriePerDay);
        mRealm.commitTransaction();
    }
}
