package nutriwiki.holmusk.com.nutriwiki;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ScrollFragment extends Fragment {


    private ExtendedObservableScrollView mScrollView;
    private ExtendedObservableScrollView.ExtendedObservableScrollViewCallback callback;
    public  static ScrollFragment newInstance() {
        return new ScrollFragment();
    }

    public void registerScrollCallback(ExtendedObservableScrollView.ExtendedObservableScrollViewCallback callback){
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ExtendedObservableScrollView) view.findViewById(R.id.scrollView);
        mScrollView.registerCallback(callback);
        MaterialViewPagerHelper.registerScrollView(getActivity(), (ObservableScrollView)mScrollView, null);
    }
}
