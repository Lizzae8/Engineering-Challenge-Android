package nutriwiki.holmusk.com.nutriwiki;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bowyer.app.fabtoolbar.FabToolbar;
import com.crashlytics.android.Crashlytics;
import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity  implements ExtendedObservableScrollView.ExtendedObservableScrollViewCallback{
    @Bind(R.id.wheel_indicator_view)
    WheelIndicatorView wheelView;
    @Bind(R.id.fab)
    FloatingActionButton actionButton;
    @Bind(R.id.fabtoolbar)
    FabToolbar fabToolbar;

    //Binds the action buttons
    @Bind(R.id.btn_logfood)
    ImageView btnLogFood;
    @Bind(R.id.btn_search)
    ImageView btnSearch;
    @Bind(R.id.btn_chart)
    ImageView btnChart;

    //Bind progress bars
    @Bind(R.id.progressBarCarb)
    ProgressBar progressBarCarb;


    private MaterialViewPager mViewPager;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        toolbar = mViewPager.getToolbar();
        //mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {

                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(false);

            }

        }

        //mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        //mDrawer.setDrawerListener(mDrawerToggle);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        ScrollFragment fragment = ScrollFragment.newInstance();
                        fragment.registerScrollCallback(MainActivity.this);
                        return fragment;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "YESTERDAY";
                    case 1:
                        return "TODAY";
                    case 2:
                        return "Professionnel";
                    case 3:
                        return "Divertissement";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.theme_primary));

                switch (page) {

                    case 0:
                        Drawable theme = getResources().getDrawable(R.mipmap.white_blue_fade_background);

                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.theme_primary,
                                colorDrawable);
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.theme_primary,
                                colorDrawable);
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.theme_primary,
                                colorDrawable);
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.theme_primary,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        initViews();
        fabToolbar.setFab(actionButton);


    }

    private void initViews() {
        //Progress bars
        progressBarCarb.setMax(100);
        progressBarCarb.setProgress(50);
        progressBarCarb.getProgressDrawable().setColorFilter(getResources().getColor(R.color.orange500), PorterDuff.Mode.SRC_IN);

        //Wheel View
        WheelIndicatorItem breakFastIncatorItem = new WheelIndicatorItem(30f , Color.parseColor("#00933B"));
        wheelView.addWheelIndicatorItem(breakFastIncatorItem);
        WheelIndicatorItem lunchIndicatorItem = new WheelIndicatorItem(25f, Color.parseColor("#F90101"));
        wheelView.addWheelIndicatorItem(lunchIndicatorItem);
        WheelIndicatorItem dinnerIncatorItem = new WheelIndicatorItem(25f, Color.parseColor("#F2B50F"));
        wheelView.addWheelIndicatorItem(dinnerIncatorItem);
        WheelIndicatorItem othersIncatorItem = new WheelIndicatorItem(10f, Color.parseColor("#0266C8"));
        wheelView.addWheelIndicatorItem(othersIncatorItem);
        wheelView.setFilledPercent(90);
        wheelView.notifyDataSetChanged();
        wheelView.startItemsAnimation();


    }

    @OnClick(R.id.fab)
    void onFabClick() {
        fabToolbar.expandFab();
    }

    @OnClick(R.id.btn_logfood)
    void onClickCall() {
        iconAnim(btnLogFood);
    }

    @OnClick(R.id.btn_search)
    void onClickEmail() {
        iconAnim(btnSearch);
    }

    @OnClick(R.id.btn_chart)
    void onClickForum() {
        iconAnim(btnChart);
    }

    private void iconAnim(View icon) {
        Animator iconAnim = ObjectAnimator.ofPropertyValuesHolder(
                icon,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.8f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.8f, 1f));
        iconAnim.start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if (fabToolbar.isFabExpanded()) {
            fabToolbar.slideOutFab();
        }
        else
            super.onBackPressed();
    }

    public void onClickEvent(View v){
        Log.e("MainActivity", "Main view clicked");
    }


    /**
     * Hide Floating Action Bar menu when user scroll the screen instead of pressing on any button.
     * @param <T>
     */
    @Override
    public <T> void onScrolled() {
       // Log.e("ExtendedScrollCallback","Fired");
        if (fabToolbar.isFabExpanded())
            fabToolbar.slideOutFab();

    }
}
