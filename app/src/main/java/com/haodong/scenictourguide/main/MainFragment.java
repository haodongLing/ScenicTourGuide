package com.haodong.scenictourguide.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.common.presenter.BaseContract;
import com.haodong.scenictourguide.main.viewpager.HorizontalPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class MainFragment extends PresenterFragment<MainContract.Presenter> implements MainContract.View {
    private HorizontalInfiniteCycleViewPager mViewpager;
    private RecyclerView mRecyclerview;
    @BindView(R.id.main_layout_jipiao)
    LinearLayout mLayoutJipiao;
    private MainAdapter mainAdapter;

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
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.canScrollVertically();
        //解决数据加载不完的问题
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        mRecyclerview.setFocusable(false);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadData();
    }



    @Override
    public MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void initAdapter(MainData mainData) {
        if (mainAdapter == null) {
            mainAdapter = new MainAdapter(R.layout.item_main, mainData.getMainDataInfos());
            mainAdapter.bindToRecyclerView(mRecyclerview);
//            mRecyclerview.setAdapter(mainAdapter);
        }
    }
}
