package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.haodong.scenictourguide.MyRequestCode;
import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.common.presenter.BaseContract;
import com.haodong.scenictourguide.location.data.LocationContact;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.location.data.ScenicListAdapter;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationFragment extends PresenterFragment<LocationContact.Presenter>{
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
        mSrlayout.setRefreshHeader(new TaurusHeader(getContext()));
        //设置 Footer 为 球脉冲 样式
        mSrlayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mSrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
              mTabLayout.setVisibility(View.GONE);
            }
        });
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager
                .VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
    }
    public void showTab(){
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
    public void showData(List<ScenicBean.ResultBean> list){
        if (mAdapter==null){
            mAdapter=new ScenicListAdapter(list);
            mRecyclerView.setAdapter(mAdapter);
        }else if (isPullToRefresh){
            mAdapter.setNewData(list);
           mAdapter.notifyDataSetChanged();
        }else {
            mAdapter.addData(list);
            mAdapter.notifyItemChanged(mAdapter.getItemCount()+3);
        }
    }
}
