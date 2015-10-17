package com.holmusk.view.components;

import com.holmusk.utils.Constants;

/**
 * Created by gmsdvt on 10/10/15.
 */
public class FoodItem {

    String name;
    Double calories;
    String portion;
    String photoUrl;

    public FoodItem(String name, Double calories, String portion) {
        this.name = name;
        this.calories = calories;
        this.portion = portion;
        this.photoUrl = Constants.DEFAULT_FOOD_PHOTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
