package nutriwiki.holmusk.com.nutriwiki;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity  {
    @Bind(R.id.wheel_indicator_view)
    WheelIndicatorView wheelView;
    @Bind(R.id.fab)
    FloatingActionButton actionButton;

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //set floating button to FabToolbar

        if (!BuildConfig.DEBUG)
            Fabric.with(this, new Crashlytics());

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
         //       actionBar.setDisplayHomeAsUpEnabled(true);
           //     actionBar.setDisplayShowHomeEnabled(true);
           //     actionBar.setDisplayShowTitleEnabled(true);
          //      actionBar.setDisplayUseLogoEnabled(false);
         //       actionBar.setHomeButtonEnabled(true);
            }
        }


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
                        return ScrollFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Selection";
                    case 1:
                        return "Actualités";
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
                ColorDrawable colorDrawable = new ColorDrawable(0xFFFFFF);

                switch (page) {

                    case 0:
                        Drawable theme = getResources().getDrawable(R.mipmap.theme_sun);

                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green,
                                colorDrawable);
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                colorDrawable);
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan,
                                colorDrawable);
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
       // MaterialViewPagerHelper.registerScrollView(this,scrollView , null);

//        View logo = findViewById(R.id.logo_white);
//        if (logo != null)
//            logo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mViewPager.notifyHeaderChanged();
//                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
//                }
//            });
        initViews();
    }

    private void initViews() {

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


        //Set up circular FAB menu
        int size = actionButton.getLayoutParams().height;
        ColorDrawable colorDrawable = new ColorDrawable(0xFF0000FF);


        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemLogfood = new ImageView(this);
        itemLogfood.setBackgroundResource(R.drawable.ic_logfood);
        SubActionButton btnLogfood = itemBuilder.setContentView(itemLogfood).build();

        ImageView itemChart = new ImageView(this);
        
        itemLogfood.setImageResource(R.drawable.ic_chart);
        SubActionButton btnChart = itemBuilder.setContentView(itemChart).build();

        ImageView itemSearch = new ImageView(this);
        itemLogfood.setBackgroundResource(R.drawable.ic_chart);
        SubActionButton btnSearch = itemBuilder.setBackgroundDrawable(colorDrawable).setContentView(itemSearch).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnLogfood)
                .addSubActionView(btnChart)
                .addSubActionView(btnSearch)
                .attachTo(actionButton)
                .build();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  super.onOptionsItemSelected(item);
    }
}
