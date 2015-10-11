package com.holmusk.model;

/**
 * Created by gmsdvt on 10/11/15.
 */

import javax.annotation.Generated;


        import java.util.ArrayList;
        import java.util.List;
        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Food {

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
    private List<Portion> portions = new ArrayList<Portion>();
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = new ArrayList<Keyword>();
    @SerializedName("length")
    @Expose
    private Integer length;

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
    public List<Portion> getPortions() {
        return portions;
    }

    /**
     *
     * @param portions
     * The portions
     */
    public void setPortions(List<Portion> portions) {
        this.portions = portions;
    }

    /**
     *
     * @return
     * The keywords
     */
    public List<Keyword> getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(List<Keyword> keywords) {
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

}