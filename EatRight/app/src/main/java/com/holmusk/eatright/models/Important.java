package com.holmusk.eatright.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * A model class to represent important information in a particular food
 *
 * Created by kai on 2015-10-06.
 */
public class Important extends RealmObject implements Serializable {

    private Nutrition sugar;
    private Nutrition sodium;
    private Nutrition saturated;
    private Nutrition cholesterol;
    private Nutrition trans;
    private Nutrition protein;
    private Nutrition calories;
    private Nutrition monounsaturated;
    private Nutrition potassium;
    private Nutrition polyunsaturated;

    @SerializedName("total_carbs")
    private Nutrition totalCarbs;

    @SerializedName("total_fats")
    private Nutrition totalFats;

    @SerializedName("dietary_fibre")
    private Nutrition dietaryFibre;

    public Nutrition getSugar() {
        return sugar;
    }

    public void setSugar(Nutrition sugar) {
        this.sugar = sugar;
    }

    public Nutrition getSodium() {
        return sodium;
    }

    public void setSodium(Nutrition sodium) {
        this.sodium = sodium;
    }

    public Nutrition getSaturated() {
        return saturated;
    }

    public void setSaturated(Nutrition saturated) {
        this.saturated = saturated;
    }

    public Nutrition getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Nutrition cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Nutrition getTrans() {
        return trans;
    }

    public void setTrans(Nutrition trans) {
        this.trans = trans;
    }

    public Nutrition getProtein() {
        return protein;
    }

    public void setProtein(Nutrition protein) {
        this.protein = protein;
    }

    public Nutrition getCalories() {
        return calories;
    }

    public void setCalories(Nutrition calories) {
        this.calories = calories;
    }

    public Nutrition getMonounsaturated() {
        return monounsaturated;
    }

    public void setMonounsaturated(Nutrition monounsaturated) {
        this.monounsaturated = monounsaturated;
    }

    public Nutrition getPotassium() {
        return potassium;
    }

    public void setPotassium(Nutrition potassium) {
        this.potassium = potassium;
    }

    public Nutrition getPolyunsaturated() {
        return polyunsaturated;
    }

    public void setPolyunsaturated(Nutrition polyunsaturated) {
        this.polyunsaturated = polyunsaturated;
    }

    public Nutrition getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(Nutrition totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public Nutrition getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(Nutrition totalFats) {
        this.totalFats = totalFats;
    }

    public Nutrition getDietaryFibre() {
        return dietaryFibre;
    }

    public void setDietaryFibre(Nutrition dietaryFibre) {
        this.dietaryFibre = dietaryFibre;
    }
}
