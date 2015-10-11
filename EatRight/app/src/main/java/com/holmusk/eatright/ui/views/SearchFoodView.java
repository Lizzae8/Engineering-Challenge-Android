package com.holmusk.eatright.ui.views;

import com.holmusk.eatright.models.Food;

import java.util.List;

/**
 * Created by kai on 2015-10-07.
 */
public interface SearchFoodView extends LoadDataView {

    // Render the search result
    void renderFoodList(List<Food> foods);
}
