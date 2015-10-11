package com.holmusk.eatright.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.holmusk.eatright.R;
import com.holmusk.eatright.models.MyFood;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kai on 2015-10-09.
 */
public class MyFoodAdapter extends BaseAdapter {

    private List<MyFood> mFoods;
    private LayoutInflater mInflater;
    private OnMyFoodClickedListener mListener;

    public MyFoodAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mFoods = new ArrayList<>();
    }

    public void setOnClickListener(OnMyFoodClickedListener listener) {
        mListener = listener;
    }

    public void setMyFoods(List<MyFood> foods) {
        mFoods.clear();
        mFoods.addAll(foods);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFoods.size();
    }

    @Override
    public MyFood getItem(int position) {
        return mFoods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_search_result, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onMyFoodClicked(mFoods.get(position));
                }
            }
        });
        holder.mFoodNameText.setText(mFoods.get(position).getName());

        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.foodName) TextView mFoodNameText;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnMyFoodClickedListener {
        void onMyFoodClicked(MyFood food);
    }

}
