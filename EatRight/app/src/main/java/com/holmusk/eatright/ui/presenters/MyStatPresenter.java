package com.holmusk.eatright.ui.presenters;

import android.content.Context;

import com.holmusk.eatright.dao.CaloriePerDayDao;
import com.holmusk.eatright.ui.views.MyStatView;

/**
 * Created by kai on 2015-10-10.
 */
public class MyStatPresenter implements Presenter {

    private CaloriePerDayDao mCalorieDao;
    private MyStatView mMyStatView;

    public MyStatPresenter(Context context) {
        mCalorieDao = new CaloriePerDayDao(context);
    }

    public void setView(MyStatView view) {
        mMyStatView = view;
    }

    public void getChartValues(boolean shouldResetData) {
        if (shouldResetData) {
            mCalorieDao.clearData();
        }
        mMyStatView.renderChart(mCalorieDao.getAllDays());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
