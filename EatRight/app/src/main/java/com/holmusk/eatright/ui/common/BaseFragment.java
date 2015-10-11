package com.holmusk.eatright.ui.common;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base Fragment class that should be extended all by Fragment in this application
 *
 * Created by kai on 2015-10-06.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    /**
     * To be implemented to initialize the initial state of the Fragment. Could
     * be used to initiate call to server or the get fragment arguments
     */
    protected abstract void initialize();

    /**
     * To be implemented by all subclass to provide generic
     * layout inflation
     *
     * @return
     */
    protected abstract int getFragmentLayout();

    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     *
     * @param view to extract each widget injected in the fragment.
     */
    private void injectViews(final View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}