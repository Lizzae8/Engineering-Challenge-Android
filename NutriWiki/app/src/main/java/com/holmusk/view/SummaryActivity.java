package com.holmusk.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bowyer.app.fabtoolbar.FabToolbar;
import com.crashlytics.android.Crashlytics;
import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import nutriwiki.holmusk.com.nutriwiki.R;

public class SummaryActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{
    @Bind(R.id.scroll_view)
    ObservableScrollView scrollView;
    @Bind(R.id.fabtoolbar)
    FabToolbar mFabToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.ic_search)
    ImageView mIcCall;
    @Bind(R.id.ic_logfood)
    ImageView mIcEmail;
    @Bind(R.id.ic_chart)
    ImageView mIcForum;
    @Bind(R.id.wheel_indicator_view)
    WheelIndicatorView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_summary);


        ButterKnife.bind(this);

        initScrollView();
        //set floating button to FabToolbar
        mFabToolbar.setFab(mFab);
    }

    private void initScrollView() {

        scrollView.setScrollViewCallbacks(this);
        WheelIndicatorItem breakFastIncatorItem = new WheelIndicatorItem(30f , Color.parseColor("#FF5722"));
        wheelView.addWheelIndicatorItem(breakFastIncatorItem);
        WheelIndicatorItem lunchIndicatorItem = new WheelIndicatorItem(25f, Color.parseColor("#0000FF"));
        wheelView.addWheelIndicatorItem(lunchIndicatorItem);
        WheelIndicatorItem dinnerIncatorItem = new WheelIndicatorItem(25f, Color.parseColor("#00FF00"));
        wheelView.addWheelIndicatorItem(dinnerIncatorItem);
        WheelIndicatorItem othersIncatorItem = new WheelIndicatorItem(10f, Color.parseColor("#00E2F0"));
        wheelView.addWheelIndicatorItem(othersIncatorItem);
        wheelView.setFilledPercent(90);
        wheelView.notifyDataSetChanged();
        wheelView.startItemsAnimation();


    }

    @Override
    public void onScrollChanged(int i, boolean b, boolean b1) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        Log.d("", "Scroll scroll scroll");
        if (scrollState == ScrollState.UP) {
            mFabToolbar.slideOutFab();
        } else if (scrollState == ScrollState.DOWN) {
            mFabToolbar.slideInFab();
        }
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        mFabToolbar.expandFab();
    }

    @OnClick(R.id.call)
    void onClickCall() {
        iconAnim(mIcCall);
    }

    @OnClick(R.id.ic_logfood)
    void onClickEmail() {
        iconAnim(mIcEmail);
    }

    @OnClick(R.id.ic_chart)
    void onClickForum() {
        iconAnim(mIcForum);
    }

    private void iconAnim(View icon) {
        Animator iconAnim = ObjectAnimator.ofPropertyValuesHolder(
                icon,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f));
        iconAnim.start();
    }

    @Override
    public void onBackPressed(){
        if (mFabToolbar.isFabExpanded())
            mFabToolbar.slideOutFab();
        else
            super.onBackPressed();
    }
}
