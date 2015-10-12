package com.holmusk.view;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.holmusk.model.Food;
import com.holmusk.model.Portion;
import com.holmusk.restapi.RestHandler;
import com.holmusk.utils.Utils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nutriwiki.holmusk.com.nutriwiki.R;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchFoodActivity extends AppCompatActivity {
    @Bind(R.id.search_view)
    MaterialSearchView searchView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView foodList;
    List<FoodItem> foodItems;
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


    }

    private void initSearchView(){
        searchView.setHint("Search food");
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.custom_cursor);

        //TODO: create query suggestion from search history
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                View mFooterView = LayoutInflater.from(SearchFoodActivity.this).inflate(R.layout.loading_view, null);


                restHandler.searchFoodWithCallback(query, new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Response<List<Food>> response, Retrofit retrofit) {
                        List<Food> foodListResult= response.body();

                        //Prepare the new food item list from the query response
                        foodItems = new ArrayList<FoodItem>();
                        for (Food foodItem:foodListResult){
                            Log.e("Food found: ",foodItem.getName());
                            List<Portion> portionList = foodItem.getPortions();

                            //Remove items with the duplicated names
                            if (!Utils.isFoodNameExisted(foodItems, foodItem.getName()))
                                foodItems.add(new FoodItem(foodItem.getName(),portionList.get(0).getNutrients().getImportant().getCalories().getValue(),portionList.get(0).getName(),""));
                        }
                        //Populate RecyclerView with new Adapter containing data
                        adapter = new FoodListAdapter(foodItems, getApplicationContext());
                        foodList.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
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
}
