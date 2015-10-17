package com.holmusk.view.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.SearchAdapter;

import nutriwiki.holmusk.com.nutriwiki.R;

/**
 * Created by gmsdvt on 10/14/15.
 */
public class MaterialSearchViewExtended extends MaterialSearchView {

    private Context mContext;

    View mTintView;
    public MaterialSearchViewExtended(Context context) {
        super(context);
        mContext = context;
    }

    public MaterialSearchViewExtended(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public MaterialSearchViewExtended(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public void setSuggestions(String[] suggestions) {
       // super.setSuggestions(suggestions);
        Drawable myIcon = getResources().getDrawable( R.drawable.ic_suggestion );
        mTintView = findViewById(R.id.transparent_view);

        super.setSuggestionIcon(myIcon);
        if (suggestions != null && suggestions.length > 0) {
            mTintView.setVisibility(VISIBLE);
            final SearchAdapter adapter = new SearchAdapter(mContext, suggestions, myIcon);
            setAdapter(adapter);

            setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Add auto search when an item is clicked

                    setQuery((String) adapter.getItem(position), true);
                }
            });
        } else {
            mTintView.setVisibility(GONE);
        }
    }
}
