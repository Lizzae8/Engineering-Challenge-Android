package com.holmusk.eatright.ui.presenters;

import android.content.Context;

import com.holmusk.eatright.dao.MyFoodDao;
import com.holmusk.eatright.models.MyFood;

/**
 * Created by kai on 2015-10-09.
 */
public class AddFoodPresenter implements Presenter {

    private MyFoodDao mMyFoodDao;

    public AddFoodPresenter(Context context) {
        mMyFoodDao = new MyFoodDao(context);
    }

    public void saveFood(MyFood food) {
        mMyFoodDao.saveFood(food);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
