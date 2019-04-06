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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haodong.scenictourguide.GuideApp;
import com.haodong.scenictourguide.MyRequestCode;
import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.common.net.RxRestClient;
import com.haodong.scenictourguide.common.net.api.NormalRequest;
import com.haodong.scenictourguide.location.data.LocationContact;
import com.haodong.scenictourguide.location.data.LocationDataConverter;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.location.data.ScenicListAdapter;
import com.haodong.scenictourguide.location.data.UrlToos;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.WeakHashMap;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationFragment extends PresenterFragment<LocationContact.Presenter>implements LocationContact.View {
    ScenicListAdapter mAdapter;
    private boolean isPullToRefresh;
    @BindView(R.id.tab_location)
    Toolbar mTabLayout;
    @BindView(R.id.srl_location)
    SmartRefreshLayout mSrlayout;
    @BindView(R.id.rv_location)
    RecyclerView mRecyclerView;
    private int currentPage;
    private int allPages;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyRequestCode.REQUEST_CODE_LOCATION && resultCode == MyResultCode
                .RESULT_CODE_LOCATION) {
            String location = data.getStringExtra("location");
            mPresenter.loadFirstPage(1,TourGuide.getConfiguration(ConfigKeys.CITY));
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadFirstPage(1,"北京");
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
                mTabLayout.setVisibility(View.GONE);
                refreshLayout.finishRefresh(2000);
            }
        });
        mSrlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                if (currentPage>allPages){
                    showNoMoreData();
                }else {
                    currentPage=currentPage+1;
                    mPresenter.loadFirstPage(currentPage,TourGuide.getConfiguration(ConfigKeys.CITY));
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
        if (mAdapter==null){
            mAdapter=new ScenicListAdapter(scenicBean.getContentlist(),true);
            mRecyclerView.setAdapter(mAdapter);
            allPages=scenicBean.getAllPages();
        } else if (isPullToRefresh) {
            mAdapter.setNewData(scenicBean.getContentlist());
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.addData(scenicBean.getContentlist());
            mAdapter.notifyItemChanged(mAdapter.getItemCount() + 3);
        }
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("tag", "onItemChildClick: " );
                startActivityForResult(new Intent(getContext(), CitypickerActivity.class),
                        MyRequestCode.REQUEST_CODE_LOCATION);
            }
        });
    }

    @Override
    public void showNoMoreData() {
        showError("no more data");
    }
}
