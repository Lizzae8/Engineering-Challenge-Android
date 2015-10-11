package com.holmusk.eatright.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.holmusk.eatright.R;
import com.holmusk.eatright.models.Food;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kai on 2015-10-07.
 */
public class SearchResultAdapter extends BaseAdapter {

    private List<Food> mFoods;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;

    public SearchResultAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mFoods = new ArrayList<>();
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void clearFood() {
        mFoods.clear();
        notifyDataSetChanged();
    }

    public void setFoods(List<Food> foods) {
        mFoods.clear();
        mFoods.addAll(foods);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFoods.size();
    }

    @Override
    public Food getItem(int position) {
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

        holder.mFoodNameText.setText(mFoods.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(mFoods.get(position));
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder {

        @Bind(R.id.foodName) TextView mFoodNameText;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Food food);
    }
}
