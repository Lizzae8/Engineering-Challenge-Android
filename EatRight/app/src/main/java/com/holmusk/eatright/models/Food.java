package com.holmusk.eatright.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * A Model class used to represent a particular food
 * Created by kai on 2015-10-06.
 */
public class Food extends RealmObject implements Serializable, BaseFood {


    @SerializedName("_id")
    @PrimaryKey
    private String id;
    private String brandName;
    private String source;
    private String name;
    private RealmList<Portion> portions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public RealmList<Portion> getPortions() {
        return portions;
    }

    public void setPortions(RealmList<Portion> portions) {
        this.portions = portions;
    }
}
