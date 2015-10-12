package com.holmusk.utils;

import com.holmusk.view.FoodItem;

import java.util.List;

/**
 * Created by gmsdvt on 10/12/15.
 */
public class Utils {
    public static boolean isFoodNameExisted(List<FoodItem> list, String name){
        for (FoodItem i:list){
            if (i.getName().equals(name))
                return true;
        }
        return false;
    }
}
