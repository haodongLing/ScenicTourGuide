package com.haodong.scenictourguide.location;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haodong.scenictourguide.DataTools;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.location.data.ImgAdapter;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.location.data.contractor.AttractionContract;
import com.haodong.scenictourguide.track.DynamicFragment;

/**
 * describe :
 * date on 2019/4/7
 * author linghailong
 * email 105354999@qq.com
 */
public class AttractionFragment extends PresenterFragment<AttractionContract.Presenter> implements AttractionContract
        .View, View.OnClickListener {
    private ScenicBean.ContentlistBean mContentlistBean = null;
    private RecyclerView mRvImg;
    private TextView mTvName, mTvContent, mTvSummery, mTvAttention, mTvLocation;
    private ImageView mIvBack, mIvCollect;
    private FrameLayout mLayoutRemark;
    private ImgAdapter mAdapter;
    private LinearLayout mLayoutBottomView;

    @Override
    public AttractionContract.Presenter initPresenter() {
        return new AttractionPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_attractions;
    }

    public static AttractionFragment newInstance() {
        return new AttractionFragment();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mTvName = root.findViewById(R.id.attraction_name);
        mTvAttention = root.findViewById(R.id.attraction_attention);
        mTvContent = root.findViewById(R.id.attraction_content);
        mTvSummery = root.findViewById(R.id.attraction_summery);
        mRvImg = root.findViewById(R.id.recycler_img);
        mRvImg.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mIvBack = root.findViewById(R.id.attraction_back);
        mIvCollect = root.findViewById(R.id.attraction_img);
        mLayoutRemark = root.findViewById(R.id.layout_remark);
        mTvLocation = root.findViewById(R.id.attraction_location);
        mLayoutBottomView = root.findViewById(R.id.attractions_bottom_view);
        mLayoutBottomView.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvCollect.setOnClickListener(this);
        mLayoutRemark.setOnClickListener(this);
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
    }

    @Override
    protected void initData() {
        super.initData();
        mContentlistBean = DataTools.getInstance().getContentListBean();
        if (mContentlistBean != null) {
            mTvName.setText(mContentlistBean.getName());
            if (mContentlistBean.getSummary() != null) {
                mTvSummery.setText(mContentlistBean.getSummary());
            }
            if (mContentlistBean.getContent() != null) {
                mTvContent.setText(mContentlistBean.getContent());
            }
            if (mContentlistBean.getAttention() != null) {
                mTvAttention.setText(mContentlistBean.getAttention());
            }
            if (mContentlistBean.getAddress() != null) {
                mTvLocation.setText(mContentlistBean.getAddress());
            }
            mAdapter = new ImgAdapter(mContentlistBean.getPicList());
            mRvImg.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attraction_back:
                break;
            case R.id.layout_remark:

                break;
            case R.id.attraction_img:
                break;
            case R.id.attractions_bottom_view:
                start(new DynamicFragment());
                break;
        }
    }

    @Override
    public boolean onBackPressed() {
        onDestroy();
        return true;
    }
}
