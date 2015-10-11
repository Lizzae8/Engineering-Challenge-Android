package com.holmusk.eatright.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.holmusk.eatright.R;
import com.holmusk.eatright.models.BaseFood;
import com.holmusk.eatright.models.Important;
import com.holmusk.eatright.models.Nutrition;
import com.holmusk.eatright.models.Portion;
import com.holmusk.eatright.ui.adapters.PortionAdapter;
import com.holmusk.eatright.ui.common.BaseActivity;
import com.holmusk.eatright.ui.presenters.FoodDetailsPresenter;
import com.holmusk.eatright.ui.views.FoodDetailsView;
import com.holmusk.eatright.util.NutritionUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class FoodDetailsActivity extends BaseActivity
        implements FoodDetailsView, AdapterView.OnItemSelectedListener {

    public static final String INTENT_EXTRA_FOOD_ID = "com.holmusk.eatright.FoodDetailActivity";
    public static final String INTENT_EXTRA_MY_FOOD = "com.holmusk.eatright.FoodDetailActivity.myfood";

    // Colors for Pie Chart
    private static final int mBlueColor = Color.rgb(26, 104, 159);
    private static final int mOrangeColor = Color.rgb(248, 86, 26);
    private static final int mGreenColor = Color.rgb(117, 222, 23);


    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.chart) PieChart mChart;
    @Bind(R.id.portions) Spinner mPortionSpinner;
    @Bind(R.id.calorie) TextView mCaloriesText;
    @Bind(R.id.fat) TextView mFatText;
    @Bind(R.id.protein) TextView mProteinText;
    @Bind(R.id.carbohydrate) TextView mCarbohydrateText;
    @Bind(R.id.saturated) TextView mSaturatedText;
    @Bind(R.id.polyunsaturated) TextView mPolyunsaturatedText;
    @Bind(R.id.monounsaturated) TextView mMonounsaturatedText;
    @Bind(R.id.cholesterol) TextView mCholesterolText;
    @Bind(R.id.sodium) TextView mSodiumText;
    @Bind(R.id.sugar) TextView mSugarText;
    @Bind(R.id.potassium) TextView mPotassiumText;

    private FoodDetailsPresenter mPresenter;
    private BaseFood mFood;
    private PortionAdapter mPortionSpinnerAdapter;
    private Portion mPortion;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_food_details;
    }

    @Override
    protected void initialize() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new FoodDetailsPresenter(this);
        mPresenter.setView(this);

        // Initialize spinner
        mPortionSpinnerAdapter = new PortionAdapter(this);
        mPortionSpinner.setAdapter(mPortionSpinnerAdapter);
        mPortionSpinner.setOnItemSelectedListener(this);

        // Initialize pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(40f);
        mChart.setDrawCenterText(true);
        // disable rotation of the chart by touch
        mChart.setRotationEnabled(false);
        // Hide legend
        mChart.getLegend().setEnabled(false);

        Bundle argBundle = getIntent().getExtras();
        String foodId = argBundle.getString(INTENT_EXTRA_FOOD_ID);
        boolean isMyFood = argBundle.getBoolean(INTENT_EXTRA_MY_FOOD, false);
        if (foodId != null) {
            mPresenter.getFoodById(foodId, isMyFood);
        }
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
    public void renderFoodDetails(BaseFood food) {
        mFood = food;
        getSupportActionBar().setTitle(food.getName());

        mPortionSpinnerAdapter.setPortions(mFood.getPortions());
    }

    @OnClick(R.id.trackCaloriesBtn)
    @Override
    public void showTrackCalorieDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.dialog_title)
                .customView(R.layout.layout_dialog_view, true)
                .positiveText(R.string.confirm)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        onCalorieAdded(materialDialog);
                    }
                })
                .show();

        View view = dialog.getView();
        TextView portionUnitText = (TextView) view.findViewById(R.id.portion_unit);
        portionUnitText.setText("Enter portion: " + mPortion.getName());
        final TextView calorieAdded = (TextView) view.findViewById(R.id.calorieAmount);
        EditText amount = (EditText) view.findViewById(R.id.amount);
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    calorieAdded.setText("");
                } else {
                    float amount = Float.parseFloat(s.toString());
                    float totalCalories = (float) (amount * mPortion.getNutrients()
                            .getImportant().getCalories().getValue());
                    calorieAdded.setText("Calories: " + Float.toString(totalCalories) + " kcal");
                }
            }
        });
    }

    private void onCalorieAdded(MaterialDialog dialog) {
        View view = dialog.getView();
        EditText amountText = (EditText) view.findViewById(R.id.amount);
        String amountString = amountText.getText().toString();
        if (amountString.length() == 0) {
            Toast.makeText(this, "No amount added", Toast.LENGTH_SHORT).show();
            return;
        }

        float amount = Float.parseFloat(amountString);
        float totalCalories = (float) (amount * mPortion.getNutrients()
                .getImportant().getCalories().getValue());
        mPresenter.addCalorie(totalCalories);
        Toast.makeText(this, "Calories tracked", Toast.LENGTH_SHORT).show();
    }

    private void setChartData(int portionPosition) {
        ArrayList<String> nutrients = new ArrayList<>();
        nutrients.add("Carbohydrate");
        nutrients.add("Fat");
        nutrients.add("Protein");

        mPortion = mFood.getPortions().get(portionPosition);
        Important important = mPortion.getNutrients().getImportant();
        renderNutritionDetails(important);
        float carbohydrate = NutritionUtil.getStandardizedValue(important.getTotalCarbs());
        float fat = NutritionUtil.getStandardizedValue(important.getTotalFats());
        float protein = NutritionUtil.getStandardizedValue(important.getProtein());

        ArrayList<Entry> values = new ArrayList<>();
        if (carbohydrate > 0) {
            values.add(new Entry(carbohydrate, 0));
        }
        if (fat > 0) {
            values.add(new Entry(fat, 1));
        }
        if (protein > 0) {
            values.add(new Entry(protein, 2));
        }

        if (values.size() > 0) {
            mChart.setCenterText("Nutritional\nSummary");
        }



        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(mBlueColor);
        colors.add(mOrangeColor);
        colors.add(mGreenColor);

        dataSet.setColors(colors);
        PieData data = new PieData(nutrients, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

    }

    /**
     * Display all nutrition values on screen
     * @param important
     */
    private void renderNutritionDetails(Important important) {
        renderValue(mCaloriesText, important.getCalories());
        renderValue(mFatText, important.getTotalFats());
        renderValue(mProteinText, important.getProtein());
        renderValue(mCarbohydrateText, important.getTotalCarbs());

        renderValue(mSaturatedText, important.getSaturated());
        renderValue(mPolyunsaturatedText, important.getPolyunsaturated());
        renderValue(mMonounsaturatedText, important.getMonounsaturated());
        renderValue(mCholesterolText, important.getCholesterol());
        renderValue(mSodiumText, important.getSodium());
        renderValue(mSugarText, important.getSugar());
        renderValue(mPotassiumText, important.getPotassium());
    }

    private void renderValue(TextView view, Nutrition nutrition) {
        if (nutrition != null) {
            view.setText(nutrition.getValue() + " " + nutrition.getUnit());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setChartData(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
