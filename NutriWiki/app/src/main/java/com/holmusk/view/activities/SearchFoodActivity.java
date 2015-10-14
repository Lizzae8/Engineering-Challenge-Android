package com.holmusk.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.holmusk.model.RecentSearch;
import com.holmusk.model.dao.DAOHandler;
import com.holmusk.model.food.Food;
import com.holmusk.model.food.Portion;
import com.holmusk.restapi.RestHandler;
import com.holmusk.utils.Constants;
import com.holmusk.utils.GoogleSearchUtil;
import com.holmusk.utils.Utils;
import com.holmusk.view.components.FoodListAdapter;
import com.holmusk.view.components.MaterialSearchViewExtended;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import nutriwiki.holmusk.com.nutriwiki.R;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchFoodActivity extends AppCompatActivity implements FoodListAdapter.OnItemClickListener, GoogleSearchUtil.GoogleQueryReturnedCallback {
    @Bind(R.id.search_view)
    MaterialSearchViewExtended searchView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView foodList;
    List<Food> foodItems;
    FoodListAdapter adapter;

    RestHandler restHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfood);
        ButterKnife.bind(this);


        restHandler = RestHandler.getInstance();
        setSupportActionBar(toolbar);
        initSearchView();

        foodList.setLayoutManager(new LinearLayoutManager(this));
        foodItems = new ArrayList<Food>();
        adapter = new FoodListAdapter(foodItems);
        adapter.setOnItemClickListener(SearchFoodActivity.this);
        foodList.setAdapter(adapter);

    }

    private void refreshSuggestionList(){
        //Get recent searches and populate the suggestion list
        RealmResults<RecentSearch> searchHistoryList = DAOHandler.getDaoHandler(SearchFoodActivity.this).getRecentSearchDAOImpl().getAllRecentSearches();
        List<String> recentList = new ArrayList<String>();

        for (RecentSearch item:searchHistoryList){
                recentList.add(item.getQuery());
        }

        String[] searchArray = new String[ recentList.size() ];
        recentList.toArray( searchArray );

        searchView.setSuggestions(searchArray);
    }
    private void initSearchView(){
        searchView.setHint("Search food");
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.custom_cursor);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                //Update database
                RecentSearch newSearch = new RecentSearch(query, new Date().getTime());
                DAOHandler.getDaoHandler(SearchFoodActivity.this).getRecentSearchDAOImpl().addOrUpdateRecentSearch(newSearch);

                //Display loading view
                foodItems.clear();
                Food loadingView = new Food();
                loadingView.setItemType(Constants.SEARCH_ITEM_TYPE_LOADING);
                foodItems.add(loadingView);
                adapter.notifyDataSetChanged();


                //Perform search action
                restHandler.searchFoodWithCallback(query, new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                        foodItems.clear();

                        List<Food> foodListResult = response.body();
                        if (foodListResult.size() == 0) {
                            Snackbar.make(findViewById(R.id.container), "No result found for item: " + query, Snackbar.LENGTH_LONG)
                                    .show();
                            foodItems.clear();
                            adapter.notifyDataSetChanged();
                        } else {
                            //Prepare the new food item list from the query response
                            for (Food foodItem : foodListResult) {
                                List<Portion> portionList = foodItem.getPortions();

                                //Remove items with the duplicated names
                                if (!Utils.isFoodNameExisted(foodItems, foodItem.getName())) {
                                    Log.e("Food found: ", "Pos:" + foodItems.size() + "name:" + foodItem.getName());
                                    foodItems.add(foodItem);

                                    try {
                                        GoogleSearchUtil.searchImageWithQuery(foodItem.getName(), foodItems.size() - 1, SearchFoodActivity.this);
                                    /*GoogleImageHandler handler = GoogleImageHandler.getInstance();
                                    HashMap<String,String> map = new HashMap<String, String>();
                                    map.put("v","1.0");
                                    map.put("start","1");
                                    map.put("imgsz","medium");
                                    map.put("q",foodItem.getName());
                                    handler.searchImageWithCallback(map, new Callback<JSONObject>() {
                                        @Override
                                        public void onResponse(Response<JSONObject> response, Retrofit retrofit) {
                                            JSONObject a = response.body();
                                            Log.e("Google  search: ",a.toString());
                                        }

                                        @Override
                                        public void onFailure(Throwable t) {
                                            t.printStackTrace();
                                        }
                                    });*/
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            //Populate RecyclerView with new Adapter containing data
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        foodItems.clear();
                        adapter.notifyDataSetChanged();

                        t.printStackTrace();
                        Snackbar.make(findViewById(R.id.container), "Error when searching for item: " + query, Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO: perform advanced search here
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                Log.e("Search view", "on Shown");
                refreshSuggestionList();
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        searchView.showSearch(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(View view, Food food) {
        Log.e("On item clicked",food.getName());
        DetailActivity.navigate(this, view.findViewById(R.id.food_photo), food);
    }

    @Override
    public <T> void onQueryReturned(int pos, String url) {
        Log.e("on Query returned", "Pos:" + pos + " Url:" + url);
        foodItems.get(pos).setPhotoUrl(url);

      // adapter.refreshUI();
        foodList.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });

    }
}
