
package com.holmusk.view.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.holmusk.model.dao.DAOHandler;
import com.holmusk.model.food.Food;
import com.holmusk.model.food.Important;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.Bind;
import butterknife.ButterKnife;
import nutriwiki.holmusk.com.nutriwiki.R;


public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.food_name)
    TextView foodName;
    @Bind(R.id.food_source)
    TextView foodSource;
    @Bind(R.id.protein_percentage)
    CircleProgressView proteinCircle;
    @Bind(R.id.carb_percentage)
    CircleProgressView carbCircle;
    @Bind(R.id.fat_percentage)
    CircleProgressView fatCircle;
    @Bind(R.id.val_carbs)
    TextView valCarbs;
    @Bind(R.id.val_fat)
    TextView valFat;
    @Bind(R.id.val_protein)
    TextView valProtein;
    @Bind(R.id.val_fibers)
    TextView valFibers;
    @Bind(R.id.val_sugars)
    TextView valSugars;
    @Bind(R.id.val_saturated_fat)
    TextView valSaturatedFat;
    @Bind(R.id.val_unsaturated_fat)
    TextView valUnsaturatedFat;
    @Bind(R.id.val_cholesterol)
    TextView valCholesterol;
    @Bind(R.id.val_sodium)
    TextView valSodium;
    @Bind(R.id.val_potassium)
    TextView valPotassium;
    @Bind(R.id.image)
    ImageView foodPhoto;


    private static final String EXTRA_IMAGE = "extraImage";
    private static final String EXTRA_TITLE = "extraTitle";
    private static final String EXTRA_FOOD = "extraFood";

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Food food;

    public static void navigate(AppCompatActivity activity, View transitionImage, Food foodItem) {

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, foodItem.getPhotoUrl());
        intent.putExtra(EXTRA_TITLE, foodItem.getName());
        intent.putExtra(EXTRA_FOOD, foodItem.getId());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //Init views
        setupToolbar();
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();


        //Process data
        String foodId = getIntent().getStringExtra(EXTRA_FOOD);
        List<Food> foodList= DAOHandler.getDaoHandler(DetailActivity.this).getFoodDAOImpl().findFoodById(foodId);
        food = foodList.get(0);

        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CardView_Light);
        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(foodPhoto, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) foodPhoto.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
        foodName.setText(food.getName() == null?"":food.getName());
        foodSource.setText(food.getSource()==null?"":"Source: "+food.getSource());

        //Load values
        setUpValues();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton) findViewById(R.id.fab), palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }

    private void setupToolbar(){

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpValues(){
        try {
            if (food != null) {
                Important important = food.getPortions().get(0).getNutrients().getImportant();
                if (important.getTotalCarbs()!=null)
                    valCarbs.setText(important.getTotalCarbs().getValue() + " " + important.getTotalCarbs().getUnit());
                else
                    valCarbs.setText("-");
                if (important.getProtein()!=null)
                    valProtein.setText(important.getProtein().getValue() + " " + important.getProtein().getUnit());
                else valProtein.setText("-");
                if (important.getTotalFats()!=null)
                    valFat.setText(important.getTotalFats().getValue() + " " + important.getTotalFats().getUnit());
                else valFat.setText("-");
                if (important.getDietaryFibre()!=null)
                    valFibers.setText(important.getDietaryFibre().getValue() + " " + important.getDietaryFibre().getUnit());
                else valFibers.setText("-");
                if (important.getSaturated()!=null)
                    valSaturatedFat.setText(important.getSaturated().getValue() + " " + important.getSaturated().getUnit());
                else valSaturatedFat.setText("-");
                if (important.getMonounsaturated()!=null && important.getPolyunsaturated()!=null)
                    valUnsaturatedFat.setText(important.getMonounsaturated().getValue() + important.getPolyunsaturated().getValue()
                        + important.getMonounsaturated().getUnit());
                else valUnsaturatedFat.setText("-");
                if (important.getCholesterol()!=null)
                    valCholesterol.setText(important.getCholesterol().getValue() + " " + important.getCholesterol().getUnit());
                else valCholesterol.setText("-");
                if (important.getSodium()!=null)
                    valSodium.setText(important.getSodium().getValue() + " " + important.getSodium().getUnit());
                else valSodium.setText("-");
                if (important.getPotassium()!=null)
                    valPotassium.setText(important.getPotassium().getValue() + " " + important.getPotassium().getUnit());
                else valPotassium.setText("-");
                if (important.getSugar()!=null)
                    valSugars.setText(important.getSugar().getValue() + " " + important.getSugar().getUnit());
                else valSugars.setText("-");

                int proteinPercent = calcProteinPercentage(important.getCalories().getValue(), important.getProtein().getValue());
                int fatPercent = calcFatPercentage(important.getCalories().getValue(),important.getTotalFats().getValue());
                setupCircleView(proteinCircle,proteinPercent );
                setupCircleView(fatCircle, fatPercent);
                setupCircleView(carbCircle, calcCarbsPercentage(proteinPercent,fatPercent));


            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int calcProteinPercentage(Double calories,Double protein_gr){
        return (int) (protein_gr*4.0/calories*100);
    }
    private int calcFatPercentage(Double calories,Double fat_gr){
        return (int) (fat_gr*9.0/calories*100);
    }
    private int calcCarbsPercentage(int proteinPercent, int fatPercent){
        return (int) (100-proteinPercent-fatPercent);
    }

    private void setupCircleView(CircleProgressView view, int value){
        //value setting
        view.setMaxValue(100);
        view.setValue(0);
        view.setValueAnimated(value);

        //show unit
        view.setUnit("%");
        view.setShowUnit(true);
    }
}
