package com.holmusk.eatright.models;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by kai on 2015-10-06.
 */
public class Nutrients extends RealmObject implements Serializable {

    private Important important;

    public Important getImportant() {
        return important;
    }

    public void setImportant(Important important) {
        this.important = important;
    }
}
