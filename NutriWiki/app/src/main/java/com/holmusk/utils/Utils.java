package com.holmusk.utils;

import com.holmusk.model.Food;

import java.util.List;

/**
 * Created by gmsdvt on 10/12/15.
 */
public class Utils {
    public static boolean isFoodNameExisted(List<Food> list, String name){
        for (Food i:list){
            if (i.getName().equals(name))
                return true;
        }
        return false;
    }
}
