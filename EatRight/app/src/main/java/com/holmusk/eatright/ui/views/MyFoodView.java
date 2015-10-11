package com.holmusk.eatright.ui.views;

import com.holmusk.eatright.models.MyFood;

import java.util.List;

/**
 * Created by kai on 2015-10-09.
 */
public interface MyFoodView {

    // Display food list on screen
    void renderMyFood(List<MyFood> foods);

    // Refresh the data after user added new food
    void refreshMyFoodList();
}
