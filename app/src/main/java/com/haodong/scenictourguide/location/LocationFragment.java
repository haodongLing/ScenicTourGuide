package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.haodong.scenictourguide.MyRequestCode;
import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.location.data.LocationContact;
import com.haodong.scenictourguide.location.data.ScenicListAdapter;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationFragment extends PresenterFragment<LocationContact.Presenter> {
    ScenicListAdapter mAdapter;
    private boolean isPullToRefresh;
    @BindView(R.id.tab_location)
    Toolbar mTabLayout;
    @BindView(R.id.srl_location)
    SmartRefreshLayout mSrlayout;
    @BindView(R.id.rv_location)
    RecyclerView mRecyclerView;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyRequestCode.REQUEST_CODE_LOCATION && resultCode == MyResultCode
                .RESULT_CODE_LOCATION) {
            String location = data.getStringExtra("location");
            Log.e("tag", "onActivityResult: -------->locationFragment" + location);
            mPresenter.loadFirstPage();
        }
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadFirstPage();
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        int primaryColor=0xffee7c3d;
        mSrlayout.setRefreshHeader(new TaurusHeader(getContext())).setPrimaryColorsId(primaryColor,primaryColor);
        //设置 Footer 为 球脉冲 样式
        mSrlayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle
                .Scale)).setPrimaryColorsId(primaryColor,primaryColor);
        mSrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mTabLayout.setVisibility(View.GONE);
                refreshLayout.finishRefresh(2000);
            }
        });
        mSrlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager
                .VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
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

//    public void showData(ArrayList<ScenicBean.ResultBean> list) {
//        if (mAdapter == null) {
//            mAdapter = new ScenicListAdapter(list, true);
//            mRecyclerView.setAdapter(mAdapter);
//        } else if (isPullToRefresh) {
//            mAdapter.setNewData(list);
//            mAdapter.notifyDataSetChanged();
//        } else {
//            mAdapter.addData(list);
//            mAdapter.notifyItemChanged(mAdapter.getItemCount() + 3);
//        }
//        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Log.e("tag", "onItemChildClick: " );
//                startActivityForResult(new Intent(getContext(), CitypickerActivity.class),
//                        MyRequestCode.REQUEST_CODE_LOCATION);
//            }
//        });
//    }
}
