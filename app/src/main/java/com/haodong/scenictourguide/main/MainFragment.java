package com.haodong.scenictourguide.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.main.viewpager.HorizontalPagerAdapter;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class MainFragment extends MyFragment {
    private HorizontalInfiniteCycleViewPager mViewpager;
    private RecyclerView mRecyclerview;

    @Override
    public Object setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mViewpager = root.findViewById(R.id.main_viewpager);
        mViewpager.setAdapter(new HorizontalPagerAdapter(getContext()));
        mRecyclerview = root.findViewById(R.id.main_recycler);
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
