package com.holmusk.model;

/**
 * Created by gmsdvt on 10/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

import io.realm.RealmObject;

@Generated("org.jsonschema2pojo")
public class Nutrients extends RealmObject implements Serializable {

    @SerializedName("important")
    @Expose
    private Important important;

    /**
     *
     * @return
     * The important
     */
    public Important getImportant() {
        return important;
    }

    /**
     *
     * @param important
     * The important
     */
    public void setImportant(Important important) {
        this.important = important;
    }



}
