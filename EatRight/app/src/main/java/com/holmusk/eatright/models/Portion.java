package com.holmusk.eatright.models;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * A model class to represent portions, which contains
 * the size of a serving and the nutrients it contains.
 *
 * Created by kai on 2015-10-06.
 */
public class Portion extends RealmObject implements Serializable {

    private String name;
    private Nutrients nutrients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }
}
