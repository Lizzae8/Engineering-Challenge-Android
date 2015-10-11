package com.holmusk.model;

/**
 * Created by gmsdvt on 10/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Portion {

    @SerializedName("nutrients")
    @Expose
    private Nutrients nutrients;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     *
     * @return
     * The nutrients
     */
    public Nutrients getNutrients() {
        return nutrients;
    }

    /**
     *
     * @param nutrients
     * The nutrients
     */
    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}