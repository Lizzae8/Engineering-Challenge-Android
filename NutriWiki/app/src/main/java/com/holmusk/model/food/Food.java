package com.holmusk.model.food;

/**
 * Created by gmsdvt on 10/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.holmusk.utils.Constants;

import java.io.Serializable;

import javax.annotation.Generated;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("org.jsonschema2pojo")
public class Food extends RealmObject implements Serializable {
    @PrimaryKey
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

    @SerializedName("uid")
    private String uid;
    private String photoUrl= Constants.DEFAULT_FOOD_PHOTO;
    private boolean isPhotoLoaded = false;
    private int itemType = Constants.SEARCH_ITEM_TYPE_FOOD;


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

    public boolean isPhotoLoaded(){
        return this.isPhotoLoaded;
    }
    public void setIsPhotoLoaded(boolean val){
        this.isPhotoLoaded = val;
    }

    public int getItemType(){
        return this.itemType;
    }
    public void setItemType(int type){
        this.itemType = type;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
}