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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.holmusk.model.RecentSearch;
import com.holmusk.model.dao.DAOHandler;
import com.holmusk.model.food.Food;
import com.holmusk.restapi.RestHandler;
import com.holmusk.utils.Constants;
import com.holmusk.utils.GoogleSearchUtil;
import com.holmusk.view.components.FoodListAdapter;
import com.holmusk.view.components.MaterialSearchViewExtended;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
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
    private boolean isShowingHistoricalData = false;
    private int GoogleQueryCounter = 0;

    //Variables used for data storing
    private String currentQuery;
    List<Food> currentResults;

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
        adapter = new FoodListAdapter(foodItems, this);
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
        recentList.toArray(searchArray);

        searchView.setSuggestions(searchArray);
    }
    private void initSearchView(){
        searchView.setHint("Search food");
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.custom_cursor);

        searchView.setOnQueryTextListener(new MaterialSearchViewExtended.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                searchView.closeSearch();
                currentQuery = query;

                //Display loading view
                foodItems.clear();
                Food loadingView = new Food();
                loadingView.setItemType(Constants.SEARCH_ITEM_TYPE_LOADING);
                foodItems.add(loadingView);
                adapter.notifyDataSetChanged();

                //Try to load results from history first
                RealmResults<RecentSearch> historyResults = DAOHandler.getDaoHandler(SearchFoodActivity.this).getRecentSearchDAOImpl().findRecentSearchByQuery(query);
                if (historyResults != null && historyResults.size() > 0) {
                    isShowingHistoricalData = true;
                    RealmList<Food> historicalFoodResults = historyResults.get(0).getResults();
                    List<Food> historicalFoodList = new ArrayList<Food>();

                    for (Food item:historicalFoodResults){
                        historicalFoodList.add(item);
                    }
                    foodItems.clear();//clear the loading view first
                    updateUIWithFoodList(historicalFoodList);
                } else {
                    isShowingHistoricalData = false;
                //If historical results for that query not found, perform search action online
                restHandler.searchFoodWithCallback(query, new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                        //Get the response data
                        List<Food> foodListResult = response.body();
                        currentResults =foodListResult;

                        //Update UI
                        foodItems.clear();


                        if (foodListResult.size() == 0) {       //If no item returned, notify the user
                            Snackbar.make(findViewById(R.id.container), "No result found for item: " + query, Snackbar.LENGTH_LONG)
                                    .show();
                            foodItems.clear();
                            adapter.notifyDataSetChanged();
                        } else {                                //Have query results to show

                            //Update UI with the returned food list
                            updateUIWithFoodList(foodListResult);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        foodItems.clear();
                        adapter.notifyDataSetChanged();
                        t.printStackTrace();
                        Snackbar.make(findViewById(R.id.container), "Error when searching for item: " + query, Snackbar.LENGTH_LONG)
                                .show();

                        //Save search history into database with empty results
                        RecentSearch newSearch = new RecentSearch(query, new Date().getTime(), new RealmList<Food>());
                        DAOHandler.getDaoHandler(SearchFoodActivity.this).getRecentSearchDAOImpl().addOrUpdateRecentSearch(newSearch);

                    }
                });

            }
                return true;
            }//End of onQueryTextSubmit

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO: perform search in advance here
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchViewExtended.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
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


    private void updateUIWithFoodList(List<Food> foodListResult){
        //Update UI with the returned food list
        if (!isShowingHistoricalData)
            GoogleQueryCounter+=foodListResult.size();
        for (Food foodItem : foodListResult) {

            //Don't display items with the same name
            //if (!Utils.isFoodNameExisted(foodItems, foodItem.getName())) {
                foodItems.add(foodItem);
                if (!isShowingHistoricalData) {
                    try {

                        GoogleSearchUtil.searchImageWithQuery(foodItem.getName(), foodItems.size() - 1, SearchFoodActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            //}
        }
        adapter.notifyDataSetChanged();

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
        if (requestCode == MaterialSearchViewExtended.REQUEST_VOICE && resultCode == RESULT_OK) {
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
        //Save item to database
        if (!isShowingHistoricalData)
            DAOHandler.getDaoHandler(this).getFoodDAOImpl().addOrUpdateFoodItem(food);

        DetailActivity.navigate(this, view.findViewById(R.id.food_photo), food);
    }

    @Override
    public <T> void onQueryReturned(int pos, String url) {

        foodItems.get(pos).setPhotoUrl(url);

      // adapter.refreshUI();
        foodList.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });

        //Update request counter and save search results into database when all photo URLs are created
        GoogleQueryCounter --;
        if (GoogleQueryCounter==0){

            //Update database with the results
            RealmList<Food> resultList = new RealmList<Food>();
            for (Food foodItem : currentResults) {
                resultList.add(foodItem);
            }

            Realm realm = Realm.getInstance(SearchFoodActivity.this);
            realm.beginTransaction();
            RecentSearch newSearch = new RecentSearch(currentQuery, new Date().getTime(), resultList);
            RecentSearch recentResults = realm.where(RecentSearch.class).equalTo("query", newSearch.getQuery()).findFirst();
            if(recentResults!=null ){
                newSearch.setEntryId(recentResults.getEntryId());
            }else{
                newSearch.setEntryId((UUID.randomUUID().toString()));
            }

            realm.copyToRealmOrUpdate(newSearch);
            realm.commitTransaction();
            realm.close();


        }

    }
}
