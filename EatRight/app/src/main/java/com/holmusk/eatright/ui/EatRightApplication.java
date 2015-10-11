package com.holmusk.eatright.ui;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by kai on 2015-10-08.
 */
public class EatRightApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
