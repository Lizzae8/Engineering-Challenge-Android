package nutriwiki.holmusk.com.nutriwiki;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bowyer.app.fabtoolbar.FabToolbar;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SummaryActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{
    @Bind(R.id.scroll_view)
    ObservableScrollView mObservableListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        ButterKnife.bind(this);
        initScrollView();
        //set floating button to FabToolbar
        mFabToolbar.setFab(mFab);
    }

    private void initScrollView() {

        mObservableListView.setScrollViewCallbacks(this);
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
