package com.holmusk.eatright.models;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by kai on 2015-10-06.
 */
public class Nutrition extends RealmObject implements Serializable {

    private String unit;
    private double value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
