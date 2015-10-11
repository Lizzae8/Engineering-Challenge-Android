package com.holmusk.eatright.ui.views;

import com.holmusk.eatright.models.BaseFood;
import com.holmusk.eatright.models.Food;

/**
 * Created by kai on 2015-10-08.
 */
public interface FoodDetailsView {

    // Display food details on screen
    void renderFoodDetails(BaseFood food);

    // Display dialog to track calorie
    void showTrackCalorieDialog();
}
