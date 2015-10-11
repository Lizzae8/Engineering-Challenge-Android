package com.holmusk.eatright.ui.presenters;

import android.content.Context;

import com.holmusk.eatright.dao.MyFoodDao;
import com.holmusk.eatright.ui.views.MyFoodView;

/**
 * Created by kai on 2015-10-09.
 */
public class MyFoodPresenter implements Presenter {

    private MyFoodView mMyFoodView;
    private MyFoodDao mMyFoodDao;

    public MyFoodPresenter(Context context) {
        mMyFoodDao = new MyFoodDao(context);
    }

    public void setView(MyFoodView view) {
        mMyFoodView = view;
    }

    public void getMyFoodList() {
        mMyFoodView.renderMyFood(mMyFoodDao.getAllMyFood());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
