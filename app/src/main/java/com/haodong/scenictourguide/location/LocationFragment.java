package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haodong.scenictourguide.MyRequestCode;
import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.location.data.ScenicListAdapter;
import com.haodong.scenictourguide.location.data.contractor.LocationContact;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

import static com.haodong.scenictourguide.location.IntentKeys.INTENT_KEY_DATA;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationFragment extends PresenterFragment<LocationContact.Presenter> implements LocationContact.View {
    ScenicListAdapter mAdapter;
    private boolean isPullToRefresh;
    @BindView(R.id.tab_location)
    AppBarLayout mTabLayout;
    @BindView(R.id.srl_location)
    SmartRefreshLayout mSrlayout;
    @BindView(R.id.rv_location)
    RecyclerView mRecyclerView;
    private int currentPage;
    private int allPages;
    private Handler mHandler = new Handler();


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyRequestCode.REQUEST_CODE_LOCATION && resultCode == MyResultCode
                .RESULT_CODE_LOCATION) {
            onLocationRechecked(data);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadFirstPage(1, "锦州");
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        int primaryColor = 0xffee7c3d;
        mSrlayout.setRefreshHeader(new TaurusHeader(getActivity()));
        //设置 Footer 为 球脉冲 样式
        mSrlayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle
                .Scale));
        mSrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isPullToRefresh = true;
                mTabLayout.setVisibility(View.GONE);
                refreshLayout.finishRefresh(2000);
                mPresenter.loadFirstPage(1, "锦州");
            }
        });
        mSrlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                isPullToRefresh = false;
                if (currentPage > allPages) {
                    showNoMoreData();
                } else {
                    currentPage = currentPage + 1;
                    mPresenter.loadMorePage(currentPage);
                }
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager
                .VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    public void showTab() {
        mTabLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_location;
    }

    @Override
    public LocationContact.Presenter initPresenter() {
        return new LocationPresenter(this);
    }

    @Override
    public void showData(ScenicBean scenicBean) {
        if (mAdapter == null) {
            mAdapter = new ScenicListAdapter(scenicBean.getContentlist(), true);
            mRecyclerView.setAdapter(mAdapter);
            allPages = scenicBean.getAllPages();
        } else if (isPullToRefresh) {
            mAdapter.setNewData(scenicBean.getContentlist());
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.addData(scenicBean.getContentlist());
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("tag", "onItemChildClick: ");
                startActivityForResult(new Intent(getContext(), CitypickerActivity.class),
                        MyRequestCode.REQUEST_CODE_LOCATION);
            }
        });
    }

    @Override
    public void showNoMoreData() {
        showError("no more data");
    }

    @Override
    public void showToolBar() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setVisibility(View.VISIBLE);
            }
        }, 1800);
    }

    @Override
    public void onLocationRechecked(Intent intent) {
        isPullToRefresh = true;
        String location = intent.getStringExtra("location");
        if (mAdapter != null) {
            mAdapter.setLocation(location);
        }
        mPresenter.loadFirstPage(1, location);
    }
}
