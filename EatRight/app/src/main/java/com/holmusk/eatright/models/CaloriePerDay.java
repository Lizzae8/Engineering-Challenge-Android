package com.holmusk.eatright.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kai on 2015-10-08.
 */
public class CaloriePerDay extends RealmObject {

    @PrimaryKey
    private int dayOfWeek;
    private float calories;

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
