package com.haodong.scenictourguide.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.hotel.contract.HotelContract;
import com.haodong.scenictourguide.hotel.data.HotelAdapter;
import com.haodong.scenictourguide.hotel.data.HotelBean;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelFragment extends PresenterFragment<HotelContract.Presenter> implements HotelContract.View {
    private HorizontalInfiniteCycleViewPager mViewPager;
    //    @BindView(R.id.rv_hotel)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_hotel)
    SmartRefreshLayout mSrlayout;
    private boolean isPullToRefresh = true;
    private HotelAdapter mAdapter;

    @Override
    public Object setLayout() {
        return R.layout.fragment_hotel;
    }

    @Override
    public HotelPresenter initPresenter() {
        return new HotelPresenter(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadFirstPage("北京");
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mViewPager = root.findViewById(R.id.hotel_viewpager);
        mViewPager.setAdapter(new HotelPagerAdapter(getContext()));
        int primaryColor = 0xffee7c3d;
        mSrlayout.setRefreshHeader(new TaurusHeader(getActivity()));
        //设置 Footer 为 球脉冲 样式
        mSrlayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle
                .Scale));
        mSrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isPullToRefresh = true;
                refreshLayout.finishRefresh(2000);
                mPresenter.loadFirstPage("北京");
            }
        });
        mRecyclerView = root.findViewById(R.id.rv_hotel);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.canScrollVertically();
        //解决数据加载不完的问题
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void showData(HotelBean hotelBean) {
        if (mAdapter == null) {
            mAdapter = new HotelAdapter(getContext(), hotelBean.getHotelLists());
            mRecyclerView.setAdapter(mAdapter);
        } else if (isPullToRefresh) {
            mAdapter.setNewData(hotelBean.getHotelLists());
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.addData(hotelBean.getHotelLists());
            mAdapter.notifyDataSetChanged();
        }
    }
}
