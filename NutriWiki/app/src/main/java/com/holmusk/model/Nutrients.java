package com.holmusk.model;

/**
 * Created by gmsdvt on 10/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Nutrients {

    @SerializedName("important")
    @Expose
    private Important important;
    @SerializedName("extra")
    @Expose
    private Extra extra;
    @SerializedName("unhandled")
    @Expose
    private List<Object> unhandled = new ArrayList<Object>();

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

    /**
     *
     * @return
     * The extra
     */
    public Extra getExtra() {
        return extra;
    }

    /**
     *
     * @param extra
     * The extra
     */
    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    /**
     *
     * @return
     * The unhandled
     */
    public List<Object> getUnhandled() {
        return unhandled;
    }

    /**
     *
     * @param unhandled
     * The unhandled
     */
    public void setUnhandled(List<Object> unhandled) {
        this.unhandled = unhandled;
    }

}
