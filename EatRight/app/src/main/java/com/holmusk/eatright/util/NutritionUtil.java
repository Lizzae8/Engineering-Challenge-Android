package com.holmusk.eatright.util;

import com.holmusk.eatright.models.Nutrition;

/**
 * Created by kai on 2015-10-09.
 */
public class NutritionUtil {

    /**
     * Convert the value and unit of nutrition into milligrams to help plot pie chart
     * @param nutrition
     * @return value in milligrams
     */
    public static float getStandardizedValue(Nutrition nutrition) {
        if (nutrition == null) {
            return 0;
        }

        double value = nutrition.getValue();
        String unit = nutrition.getUnit();

        if (unit.equalsIgnoreCase("g")) {
            return (float) (value * 1000);
        } else if (unit.equalsIgnoreCase("mg")) {
            return (float) value;
        }

        return 0;
    }
}
