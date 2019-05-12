package com.haodong.scenictourguide.track;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;

import com.haodong.scenictourguide.MainActivity;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.common.presenter.BaseContract;
import com.haodong.scenictourguide.main.MainAdapter;
import com.haodong.scenictourguide.main.MainData;
import com.haodong.scenictourguide.main.MainDataManager;
import com.haodong.scenictourguide.track.contractor.TrackContrator;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.genius.ui.widget.TextView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class TrackFragment extends PresenterFragment<TrackContrator.Presennter> implements TrackContrator.View,View.OnClickListener {
    private FloatActionButton mAction;
    @BindView(R.id.rv_track)
    RecyclerView mRecyclerview;
    Button mTvTrack;
    private MainAdapter mainAdapter;
    @BindView(R.id.srl_track)
    SmartRefreshLayout mSrlayout;


    @Override
    public Object setLayout() {
        return R.layout.fragment_track;
    }


    @Override
    public void setPresenter(TrackContrator.Presennter presenter) {

    }

    @Override
    public TrackPresenter initPresenter() {
        return new TrackPresenter(this);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mTvTrack=root.findViewById(R.id.tv_zuji);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.canScrollVertically();
        //解决数据加载不完的问题
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        mRecyclerview.setFocusable(false);
        mAction = root.findViewById(R.id.track_btn_action);
        mAction.setOnClickListener(this);

        mSrlayout.setRefreshHeader(new TaurusHeader(getActivity()));
        //设置 Footer 为 球脉冲 样式
        mSrlayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle
                .Scale));
        mSrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
//                isPullToRefresh = true;
//                mTabLayout.setVisibility(View.GONE);
                refreshLayout.finishRefresh(2000);
//                mPresenter.loadFirstPage(1, "锦州");
                setData(MainDataManager.getDefault().getMainDatas());
            }
        });
        mSrlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
//                isPullToRefresh = false;
//                if (currentPage > allPages) {
//                    showNoMoreData();
//                } else {
//                    currentPage = currentPage + 1;
//                    mPresenter.loadMorePage(currentPage);
//                }
            }
        });

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mTvTrack.setText(MainDataManager.getDefault().getMainDatas().size()+"条");
        setData(MainDataManager.getDefault().getMainDatas());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        float transY = 0;
        float rotation = 0;
        if (isVisibleToUser) {
            // 对浮动按钮进行隐藏与显示的动画
            // transY 默认为0 则显示
            rotation = 360;
        } else {
            transY = Ui.dipToPx(getResources(), -76);
        }
        // 开始动画
        // 旋转，Y轴位移，弹性差值器，时间
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.track_btn_action:
                startActivity(new Intent(getContext(),EditTrackActivity.class));
                break;
        }
    }
    private void setData(List<MainData.MainDataInfo> mainDataInfos){
        if (mainAdapter==null){
            mainAdapter=new MainAdapter(R.layout.item_main,mainDataInfos);
            mRecyclerview.setAdapter(mainAdapter);
        }else {
            mainAdapter.setNewData(mainDataInfos);
            mainAdapter.notifyDataSetChanged();
        }
        mTvTrack.setText(MainDataManager.getDefault().getMainDatas().size()+"条");
    }

}
