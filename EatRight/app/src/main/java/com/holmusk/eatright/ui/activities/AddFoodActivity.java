package com.holmusk.eatright.ui.activities;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.holmusk.eatright.R;
import com.holmusk.eatright.models.Important;
import com.holmusk.eatright.models.MyFood;
import com.holmusk.eatright.models.Nutrients;
import com.holmusk.eatright.models.Nutrition;
import com.holmusk.eatright.models.Portion;
import com.holmusk.eatright.ui.common.BaseActivity;
import com.holmusk.eatright.ui.presenters.AddFoodPresenter;
import com.iangclifton.android.floatlabel.FloatLabel;

import butterknife.Bind;
import io.realm.RealmList;

public class AddFoodActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.foodName) FloatLabel mFoodNameText;
    @Bind(R.id.portion) FloatLabel mPortionText;
    @Bind(R.id.calories) FloatLabel mCalorieText;
    @Bind(R.id.protein) FloatLabel mProteinText;
    @Bind(R.id.carbohydrate) FloatLabel mCarbohydrateText;
    @Bind(R.id.fat) FloatLabel mFatText;
    @Bind(R.id.dietary_fibre) FloatLabel mDietaryFibreText;
    @Bind(R.id.trans) FloatLabel mTransText;
    @Bind(R.id.saturated) FloatLabel mSaturatedText;
    @Bind(R.id.polyunsaturated) FloatLabel mPolyText;
    @Bind(R.id.monounsaturated) FloatLabel mMonoText;
    @Bind(R.id.sodium) FloatLabel mSodiumText;
    @Bind(R.id.potassium) FloatLabel mPotassiumText;
    @Bind(R.id.sugar) FloatLabel mSugarText;
    @Bind(R.id.cholesterol) FloatLabel mCholesterolText;

    private AddFoodPresenter mPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_food;
    }

    @Override
    protected void initialize() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new AddFoodPresenter(this);
    }

    private void saveMyFood() {

        String foodName = mFoodNameText.getEditText().getText().toString();
        if (foodName.length() < 1) {
            Toast.makeText(this, "Please enter food name", Toast.LENGTH_LONG).show();
            return;
        }

        String portionName = mPortionText.getEditText().getText().toString();
        if (portionName.length() < 1) {
            Toast.makeText(this, "Please enter portion", Toast.LENGTH_LONG).show();
            return;
        }

        String calorie =  mCalorieText.getEditText().getText().toString();
        if (calorie.length() < 1) {
            Toast.makeText(this, "Please enter calorie", Toast.LENGTH_LONG).show();
            return;
        }

        MyFood food = new MyFood();
        food.setName(foodName);
        RealmList<Portion> portions = new RealmList<>();
        Portion portion = new Portion();
        portion.setName(portionName);
        Nutrients nutrients = new Nutrients();
        Important important = new Important();

        important.setCalories(getFloatLabelNutrition(mCalorieText, "kcal"));
        important.setProtein(getFloatLabelNutrition(mProteinText, "mg"));
        important.setTotalCarbs(getFloatLabelNutrition(mCarbohydrateText, "mg"));
        important.setTotalFats(getFloatLabelNutrition(mFatText, "mg"));
        important.setDietaryFibre(getFloatLabelNutrition(mDietaryFibreText, "mg"));
        important.setTrans(getFloatLabelNutrition(mTransText, "mg"));
        important.setSaturated(getFloatLabelNutrition(mSaturatedText, "mg"));
        important.setPolyunsaturated(getFloatLabelNutrition(mPolyText, "mg"));
        important.setMonounsaturated(getFloatLabelNutrition(mMonoText, "mg"));
        important.setSodium(getFloatLabelNutrition(mSodiumText, "mg"));
        important.setPolyunsaturated(getFloatLabelNutrition(mPotassiumText, "mg"));
        important.setSugar(getFloatLabelNutrition(mSugarText, "mg"));
        important.setCholesterol(getFloatLabelNutrition(mCholesterolText, "mg"));

        nutrients.setImportant(important);
        portion.setNutrients(nutrients);
        portions.add(portion);
        food.setPortions(portions);
        mPresenter.saveFood(food);

    }

    private Nutrition getFloatLabelNutrition(FloatLabel label, String unit) {
        Nutrition nutrition = new Nutrition();
        nutrition.setUnit(unit);
        String value =  label.getEditText().getText().toString();
        if (value.length() > 0) {
            nutrition.setValue(Double.parseDouble(value));
        } else {
            nutrition.setValue(0);
        }
        return nutrition;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_add:
                // add food
                saveMyFood();
                setResult(RESULT_OK);
                finish();
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

}
