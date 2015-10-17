package com.holmusk.view.components;

import android.content.Context;
import android.util.AttributeSet;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by gmsdvt on 10/8/15.
 * Register additional onScrollChanged() callback so that the scrollview can trigger additional action.
 */
public class ExtendedObservableScrollView extends ObservableScrollView {
    public interface ExtendedObservableScrollViewCallback{
        public <T> void onScrolled();
    }
    ExtendedObservableScrollViewCallback callback;


    public void registerCallback(ExtendedObservableScrollViewCallback callback){
        this.callback = callback;
    }

    public ExtendedObservableScrollView(Context context) {
        super(context);
    }

    public ExtendedObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        callback.onScrolled();
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
