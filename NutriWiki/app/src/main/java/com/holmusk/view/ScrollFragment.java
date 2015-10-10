package com.holmusk.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import nutriwiki.holmusk.com.nutriwiki.R;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ScrollFragment extends Fragment {

    private ExtendedObservableScrollView mScrollView;
    private ExtendedObservableScrollView.ExtendedObservableScrollViewCallback callback;
    RecyclerView rv;
    private List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.avatar_denholm));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.avatar_douglas));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.avatar_jen));
    }
    public  static ScrollFragment newInstance() {
        return new ScrollFragment();
    }


    public void registerScrollCallback(ExtendedObservableScrollView.ExtendedObservableScrollViewCallback callback){
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        initializeData();
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
        // rv.setHasFixedSize(true);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ExtendedObservableScrollView) view.findViewById(R.id.scrollView);
        mScrollView.registerCallback(callback);
        MaterialViewPagerHelper.registerScrollView(getActivity(), (ObservableScrollView)mScrollView, null);
    }
}
