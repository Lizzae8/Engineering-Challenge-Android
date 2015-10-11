
package com.holmusk.eatright.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.holmusk.eatright.R;
import com.holmusk.eatright.models.MyFood;
import com.holmusk.eatright.ui.adapters.MyFoodAdapter;
import com.holmusk.eatright.ui.common.BaseFragment;
import com.holmusk.eatright.ui.presenters.MyFoodPresenter;
import com.holmusk.eatright.ui.views.MyFoodView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyFoodFragment extends BaseFragment
        implements MyFoodView, MyFoodAdapter.OnMyFoodClickedListener {

    private MyFoodPresenter mPresenter;
    private MyFoodFragmentListener mListener;
    private MyFoodAdapter mAdapter;

    @Bind(R.id.emptyFoodMsg) TextView emptyFoodText;
    @Bind(R.id.myFoodList) ListView mMyFoodList;

    public static MyFoodFragment newInstance() {
        MyFoodFragment fragment = new MyFoodFragment();
        return fragment;
    }

    @Override
    protected void initialize() {
        mPresenter = new MyFoodPresenter(getActivity());
        mPresenter.setView(this);
        mAdapter = new MyFoodAdapter(getActivity());
        mAdapter.setOnClickListener(this);
        mMyFoodList.setAdapter(mAdapter);

        refreshMyFoodList();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_my_food;
    }

    public void refreshMyFoodList() {
        mPresenter.getMyFoodList();
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        if (mListener != null) {
            mListener.onAddFoodClicked();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            try {
                mListener = (MyFoodFragmentListener) context;
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

    @Override
    public void renderMyFood(List<MyFood> foods) {
        mAdapter.setMyFoods(foods);
        if (foods.size() > 0) {
            emptyFoodText.setVisibility(View.GONE);
        } else {
            emptyFoodText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMyFoodClicked(MyFood food) {
        mListener.onFoodClicked(food);
    }

    public interface MyFoodFragmentListener {

        /**
         * Called when user clicked on fab to add food
         */
        void onAddFoodClicked();

        /**
         * Called when user clicked on food to see details
         */
        void onFoodClicked(MyFood food);
    }

}
