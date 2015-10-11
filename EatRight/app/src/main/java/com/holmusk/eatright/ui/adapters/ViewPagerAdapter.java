package com.holmusk.eatright.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.holmusk.eatright.ui.fragments.MyFoodFragment;
import com.holmusk.eatright.ui.fragments.MyStatFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to display fragments into TabLayout
 *
 * Created by kai on 2015-10-06.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(MyStatFragment.newInstance());
        mFragments.add(MyFoodFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void refreshMyFoodFragment() {
        if (mFragments.size() > 1) {
            MyFoodFragment fragment = (MyFoodFragment) mFragments.get(1);
            fragment.refreshMyFoodList();
        }
    }

    public void refreshMyStatFragment() {
        if (mFragments.size() > 0) {
            MyStatFragment fragment = (MyStatFragment) mFragments.get(0);
            fragment.refreshChart();
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
