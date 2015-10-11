package com.holmusk.eatright.ui.views;

import com.holmusk.eatright.models.CaloriePerDay;

import java.util.List;

/**
 * Created by kai on 2015-10-09.
 */
public interface MyStatView {

    // Show the chart for calorie intake
    void renderChart(List<CaloriePerDay> data);

    // Refresh the chart with new data in case user tracked more calorie
    void refreshChart();
}
