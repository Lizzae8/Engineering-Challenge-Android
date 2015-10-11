package com.holmusk.eatright.ui.activities;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.holmusk.eatright.R;
import com.holmusk.eatright.models.Food;
import com.holmusk.eatright.ui.adapters.SearchResultAdapter;
import com.holmusk.eatright.ui.common.BaseActivity;
import com.holmusk.eatright.ui.presenters.SearchFoodPresenter;
import com.holmusk.eatright.ui.views.SearchFoodView;

import java.util.List;

import butterknife.Bind;

public class SearchActivity extends BaseActivity
        implements SearchFoodView, SearchResultAdapter.OnItemClickListener {

    private SearchFoodPresenter mPresenter;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.searchResultList) ListView mSearchSuggestionList;
    @Bind(R.id.loading) ProgressBar mLoadingProgressBar;

    private SearchView mSearchView;
    private SearchResultAdapter mAdapter;

    // Indicator with user submitted any query
    private boolean mSearched = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search;
    }

    @Override
    protected void initialize() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new SearchFoodPresenter(this);
        mPresenter.setView(this);

        mAdapter = new SearchResultAdapter(this);
        mSearchSuggestionList.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        setUpSearchView(searchMenuItem);
        return true;
    }

    private void setUpSearchView(MenuItem searchMenuItem) {
        // Expand the search menu item in order to show by default the query
        searchMenuItem.expandActionView();
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);

        // Set SearchView to take whole screen width because it doesn't fill screen width
        // by default on tablet
        mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                if (!mSearched) {
                    finish();
                    return false;
                }
                return true;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    mPresenter.searchFood(newText);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void renderFoodList(List<Food> foods) {
        mAdapter.setFoods(foods);
        if (foods.size() == 0) {
            Toast.makeText(this, "No result found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showLoading() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        mAdapter.clearFood();
    }

    @Override
    public void hideLoading() {
        mLoadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(Food food) {
        mPresenter.openFoodDetails(food, this);
    }
}
