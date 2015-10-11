package com.holmusk.eatright.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.holmusk.eatright.R;
import com.holmusk.eatright.models.MyFood;
import com.holmusk.eatright.ui.adapters.ViewPagerAdapter;
import com.holmusk.eatright.ui.common.BaseActivity;
import com.holmusk.eatright.ui.fragments.MyFoodFragment;
import com.holmusk.eatright.ui.fragments.MyStatFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements MyFoodFragment.MyFoodFragmentListener,
        MyStatFragment.MyStatFragmentListener{

    public static final int REQUEST_CODE_ADD_FOOD = 909;
    public static final int REQUEST_CODE_TRACK = 606;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.toolbarTitle) TextView mToolbarTitleText;
    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;

    private ViewPagerAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialize() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/lobster.otf");
        mToolbarTitleText.setTypeface(tf);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setupViewPager();

        mTabLayout.setupWithViewPager(mViewPager);
        setUpTabLayout();
    }

    private void setupViewPager() {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    private void setUpTabLayout() {
        int tabCount = mTabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            if (i == 0) {
                mTabLayout.getTabAt(i).setIcon(R.drawable.ic_home_white_36dp);
            } else if (i == 1) {
                mTabLayout.getTabAt(i).setIcon(R.drawable.ic_local_dining_white_36dp);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            onSearchClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddFoodClicked() {
        Intent intent = new Intent(this, AddFoodActivity.class);
        startActivityForResult(intent, MainActivity.REQUEST_CODE_ADD_FOOD);
    }

    @Override
    public void onFoodClicked(MyFood food) {
        Intent intent = new Intent(this, FoodDetailsActivity.class);
        intent.putExtra(FoodDetailsActivity.INTENT_EXTRA_FOOD_ID, food.getName());
        intent.putExtra(FoodDetailsActivity.INTENT_EXTRA_MY_FOOD, true);
        startActivityForResult(intent, REQUEST_CODE_TRACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TRACK) {
            mAdapter.refreshMyStatFragment();
        } else if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_ADD_FOOD) {
            // Call adapter to refresh MyFoodFragment to reflect new added Food
            mAdapter.refreshMyFoodFragment();
        }
    }

    @Override
    public void onSearchClicked() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent, REQUEST_CODE_TRACK);
    }
}
