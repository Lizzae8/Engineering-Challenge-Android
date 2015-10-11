package com.holmusk.eatright.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Base activity created to be extended by every activity in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every activity.
 *
 * Created by kai on 2015-10-06.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        injectViews();
        initialize();
    }

    /**
     * To be implemented by all subclass to provide generic
     * layout inflation
     * @return
     */
    protected abstract int getLayoutResource();


    /**
     * Do all initialization of activity
     */
    protected abstract void initialize();

    /**
     * Replace every field annotated with ButterKnife annotations like @Bind with the proper
     * value.
     */
    private void injectViews() {
        ButterKnife.bind(this);
    }
}