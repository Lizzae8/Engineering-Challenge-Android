package com.holmusk.model;

/**
 * Created by gmsdvt on 10/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

import io.realm.RealmList;
import io.realm.RealmObject;

@Generated("org.jsonschema2pojo")
public class Food extends RealmObject implements Serializable {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("portions")
    @Expose
    private RealmList<Portion> portions = new RealmList<Portion>();
    @SerializedName("keywords")
    @Expose
    private RealmList<Keyword> keywords = new RealmList<Keyword>();
    @SerializedName("length")
    @Expose
    private Integer length;

    private String photoUrl="";

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
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

    /**
     *
     * @return
     * The portions
     */
    public RealmList<Portion> getPortions() {
        return portions;
    }

    /**
     *
     * @param portions
     * The portions
     */
    public void setPortions(RealmList<Portion> portions) {
        this.portions = portions;
    }

    /**
     *
     * @return
     * The keywords
     */
    public RealmList<Keyword> getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(RealmList<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The length
     */
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param length
     * The length
     */
    public void setLength(Integer length) {
        this.length = length;
    }


    public String getPhotoUrl(){
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}