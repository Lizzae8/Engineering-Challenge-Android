package com.holmusk.eatright.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;
import com.holmusk.eatright.R;
import com.holmusk.eatright.models.CaloriePerDay;
import com.holmusk.eatright.ui.common.BaseFragment;
import com.holmusk.eatright.ui.presenters.MyStatPresenter;
import com.holmusk.eatright.ui.views.MyStatView;

import net.grandcentrix.tray.TrayAppPreferences;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyStatFragment extends BaseFragment implements MyStatView {

    private static final String PREF_DATA_SHOULD_RESET = "com.holmusk.MyStatFragment";

    private MyStatPresenter mPresenter;
    private TrayAppPreferences appPreferences;
    private MyStatFragmentListener mListener;

    @Bind(R.id.linechart) LineChartView mChartView;

    public static MyStatFragment newInstance() {
        MyStatFragment fragment = new MyStatFragment();
        return fragment;
    }

    @Override
    protected void initialize() {
        mPresenter = new MyStatPresenter(getActivity());
        mPresenter.setView(this);

        appPreferences = new TrayAppPreferences(getActivity());
        DateTime dateTime = DateTime.now();
        int dayOfWeek = dateTime.getDayOfWeek();
        boolean resetData;

        if (dayOfWeek == 1) {
            // It's Monday, check if data should be reset
            resetData = appPreferences.getBoolean(PREF_DATA_SHOULD_RESET, false);
        } else {
            // Not Monday, don't reset data
            resetData = false;
            // Set to true so that when it's Monday it resets again
            appPreferences.put(PREF_DATA_SHOULD_RESET, true);
        }

        mPresenter.getChartValues(resetData);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_my_stat;
    }

    public void refreshChart() {
        mPresenter.getChartValues(false);
    }

    @OnClick(R.id.searchBtn)
    public void onSearchClicked() {
        if (mListener != null) {
            mListener.onSearchClicked();
        }
    }

    @OnClick(R.id.resetDataBtn)
    public void resetData() {
        mPresenter.getChartValues(true);
        Toast.makeText(getActivity(), "Data reset", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void renderChart(List<CaloriePerDay> data) {
        String[] days = getResources().getStringArray(R.array.days_array);

        float[] caloryData = new float[days.length];
        for (int i = 0; i < data.size(); i++) {
            caloryData[data.get(i).getDayOfWeek()-1] = data.get(i).getCalories();
        }

        float max = getMaxValue(caloryData);
        int step;

        if (max <= 5) {
            max = 5;
            step = 1;
        } else {
            step = (int) (max / 5f);
        }

        if (mChartView.getData().size() > 0) {
            mChartView.setAxisBorderValues(0, (int) max, step);

            mChartView.updateValues(0, caloryData);
            mChartView.notifyDataUpdate();
            mChartView.show();
            return;
        }
        LineSet dataSet = new LineSet(days, caloryData);
        dataSet.setColor(Color.parseColor("#FF4081"))
                .setFill(Color.parseColor("#FF4081"))
                .setSmooth(true);
        mChartView.addData(dataSet);

        mChartView.setTopSpacing(Tools.fromDpToPx(5))
                .setBorderSpacing(Tools.fromDpToPx(0))
                .setAxisBorderValues(0, (int) max, step)
                .setXLabels(AxisController.LabelPosition.OUTSIDE)
                .setYLabels(AxisController.LabelPosition.OUTSIDE)
                .setLabelsColor(Color.parseColor("#ffffff"))
                .setAxisColor(Color.parseColor("#ffffff"))
                .setXAxis(false);

        mChartView.show();
    }

    private float getMaxValue(float[] values) {
        float max = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            try {
                mListener = (MyStatFragmentListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString()
                        + " must implement MyFoodFragmentListener");
            }
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface MyStatFragmentListener {

        void onSearchClicked();
    }

}
