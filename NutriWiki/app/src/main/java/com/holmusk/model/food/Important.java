package com.holmusk.model.food;

/**
 * Created by gmsdvt on 10/11/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

import io.realm.RealmObject;

@Generated("org.jsonschema2pojo")
public class Important extends RealmObject implements Serializable {

    @SerializedName("sugar")
    @Expose
    private Sugar sugar;
    @SerializedName("monounsaturated")
    @Expose
    private Monounsaturated monounsaturated;
    @SerializedName("protein")
    @Expose
    private Protein protein;
    @SerializedName("dietary_fibre")
    @Expose
    private DietaryFibre dietaryFibre;
    @SerializedName("calories")
    @Expose
    private Calories calories;
    @SerializedName("total_fats")
    @Expose
    private TotalFats totalFats;
    @SerializedName("potassium")
    @Expose
    private Potassium potassium;
    @SerializedName("total_carbs")
    @Expose
    private TotalCarbs totalCarbs;
    @SerializedName("sodium")
    @Expose
    private Sodium sodium;
    @SerializedName("trans")
    @Expose
    private TransFat trans;
    @SerializedName("saturated")
    @Expose
    private Saturated saturated;
    @SerializedName("cholesterol")
    @Expose
    private Cholesterol cholesterol;
    @SerializedName("polyunsaturated")
    @Expose
    private Polyunsaturated polyunsaturated;

    /**
     *
     * @return
     * The sugar
     */
    public Sugar getSugar() {
        return sugar;
    }

    /**
     *
     * @param sugar
     * The sugar
     */
    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
    }

    /**
     *
     * @return
     * The monounsaturated
     */
    public Monounsaturated getMonounsaturated() {
        return monounsaturated;
    }

    /**
     *
     * @param monounsaturated
     * The monounsaturated
     */
    public void setMonounsaturated(Monounsaturated monounsaturated) {
        this.monounsaturated = monounsaturated;
    }

    /**
     *
     * @return
     * The protein
     */
    public Protein getProtein() {
        return protein;
    }

    /**
     *
     * @param protein
     * The protein
     */
    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    /**
     *
     * @return
     * The dietaryFibre
     */
    public DietaryFibre getDietaryFibre() {
        return dietaryFibre;
    }

    /**
     *
     * @param dietaryFibre
     * The dietary_fibre
     */
    public void setDietaryFibre(DietaryFibre dietaryFibre) {
        this.dietaryFibre = dietaryFibre;
    }

    /**
     *
     * @return
     * The calories
     */
    public Calories getCalories() {
        return calories;
    }

    /**
     *
     * @param calories
     * The calories
     */
    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    /**
     *
     * @return
     * The totalFats
     */
    public TotalFats getTotalFats() {
        return totalFats;
    }

    /**
     *
     * @param totalFats
     * The total_fats
     */
    public void setTotalFats(TotalFats totalFats) {
        this.totalFats = totalFats;
    }

    /**
     *
     * @return
     * The potassium
     */
    public Potassium getPotassium() {
        return potassium;
    }

    /**
     *
     * @param potassium
     * The potassium
     */
    public void setPotassium(Potassium potassium) {
        this.potassium = potassium;
    }

    /**
     *
     * @return
     * The totalCarbs
     */
    public TotalCarbs getTotalCarbs() {
        return totalCarbs;
    }

    /**
     *
     * @param totalCarbs
     * The total_carbs
     */
    public void setTotalCarbs(TotalCarbs totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    /**
     *
     * @return
     * The sodium
     */
    public Sodium getSodium() {
        return sodium;
    }

    /**
     *
     * @param sodium
     * The sodium
     */
    public void setSodium(Sodium sodium) {
        this.sodium = sodium;
    }

    /**
     *
     * @return
     * The trans
     */
    public TransFat getTrans() {
        return trans;
    }

    /**
     *
     * @param trans
     * The trans
     */
    public void setTrans(TransFat trans) {
        this.trans = trans;
    }

    /**
     *
     * @return
     * The saturated
     */
    public Saturated getSaturated() {
        return saturated;
    }

    /**
     *
     * @param saturated
     * The saturated
     */
    public void setSaturated(Saturated saturated) {
        this.saturated = saturated;
    }

    /**
     *
     * @return
     * The cholesterol
     */
    public Cholesterol getCholesterol() {
        return cholesterol;
    }

    /**
     *
     * @param cholesterol
     * The cholesterol
     */
    public void setCholesterol(Cholesterol cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     *
     * @return
     * The polyunsaturated
     */
    public Polyunsaturated getPolyunsaturated() {
        return polyunsaturated;
    }

    /**
     *
     * @param polyunsaturated
     * The polyunsaturated
     */
    public void setPolyunsaturated(Polyunsaturated polyunsaturated) {
        this.polyunsaturated = polyunsaturated;
    }

}
