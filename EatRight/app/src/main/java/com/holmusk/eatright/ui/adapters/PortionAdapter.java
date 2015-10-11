package com.holmusk.eatright.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.holmusk.eatright.models.Portion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai on 2015-10-08.
 */
public class PortionAdapter extends ArrayAdapter<String> {

    private List<Portion> mPortions;

    public PortionAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_dropdown_item);
        mPortions = new ArrayList<>();
    }

    public void setPortions(List<Portion> portions) {
        mPortions.clear();
        mPortions.addAll(portions);
        // Clear drop down option
        super.clear();
        for (int i = 0; i < mPortions.size(); i++) {
            // Add drop down option
            super.add(mPortions.get(i).getName());
        }
        notifyDataSetChanged();
    }


}
