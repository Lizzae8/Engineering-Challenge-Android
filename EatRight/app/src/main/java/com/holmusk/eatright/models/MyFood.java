package com.holmusk.eatright.models;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kai on 2015-10-09.
 */
public class MyFood extends RealmObject implements Serializable, BaseFood {

    @PrimaryKey
    private String name;
    private RealmList<Portion> portions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public RealmList<Portion> getPortions() {
        return portions;
    }

    public void setPortions(RealmList<Portion> portion) {
        this.portions = portion;
    }
}
