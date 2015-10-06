package com.holmusk.eatright.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.holmusk.eatright.ui.fragments.MyFoodFragment;
import com.holmusk.eatright.ui.fragments.MyStatFragment;

/**
 * Adapter to display fragments into TabLayout
 *
 * Created by kai on 2015-10-06.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mFragmentTitle = {"My Stat", "My Food"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CharSequence getPageTitle(int position) {
        return mFragmentTitle[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MyStatFragment.newInstance();
            case 1:
                return MyFoodFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFragmentTitle.length;
    }
}
