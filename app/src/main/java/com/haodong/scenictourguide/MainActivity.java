package com.haodong.scenictourguide;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haodong.scenictourguide.common.app.activities.MyActivity;

import butterknife.BindView;

public class MainActivity extends MyActivity implements View.OnClickListener {
    @BindView(R.id.tab_bottom_main)
    LinearLayout tabMain;
    @BindView(R.id.tab_bottom_location)
    LinearLayout tabLocation;
    @BindView(R.id.tab_bottom_hotel)
    LinearLayout tabHotel;
    @BindView(R.id.tab_bottom_track)
    LinearLayout tabTrack;
    @BindView(R.id.tab_bottom_mine)
    LinearLayout tabMine;

    @BindView(R.id.iv_tab_main)
    ImageView mIvMain;
    @BindView(R.id.iv_tab_location)
    ImageView mIvLocation;
    @BindView(R.id.iv_tab_hotel)
    ImageView mIvHotel;
    @BindView(R.id.iv_tab_track)
    ImageView mIvTrack;
    @BindView(R.id.iv_tab_mine)
    ImageView mIvMine;

    @BindView(R.id.tv_main_main)
    TextView mTvMain;
    @BindView(R.id.tv_main_location)
    TextView mTvLocation;
    @BindView(R.id.tv_main_hotel)
    TextView mTvHotel;
    @BindView(R.id.tv_main_track)
    TextView mTvTrack;
    @BindView(R.id.tv_main_mine)
    TextView mTvMine;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected void initWidget() {
        tabMain.setOnClickListener(this);
        tabLocation.setOnClickListener(this);
        tabHotel.setOnClickListener(this);
        tabTrack.setOnClickListener(this);
        tabMine.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        resetTabState();
        switch (v.getId()) {
            case R.id.tab_bottom_main:
                mIvMain.setImageResource(R.drawable.ic_main_color);
                mTvMain.setTextColor(Color.parseColor("#EE7C3D"));
                break;
            case R.id.tab_bottom_location:
                mIvLocation.setImageResource(R.drawable.ic_location);
                mTvLocation.setTextColor(Color.parseColor("#EE7C3D"));
                break;
            case R.id.tab_bottom_hotel:
                mIvHotel.setImageResource(R.drawable.ic_hotel_color);
                mTvHotel.setTextColor(Color.parseColor("#EE7C3D"));
                break;
            case R.id.tab_bottom_track:
                mIvTrack.setImageResource(R.drawable.ic_track_color);
                mTvTrack.setTextColor(Color.parseColor("#EE7C3D"));
                break;
            case R.id.tab_bottom_mine:
                mIvMine.setImageResource(R.drawable.ic_mine_color);
                mTvMine.setTextColor(Color.parseColor("#EE7C3D"));
                break;
        }
    }

    private void resetTabState() {
        mIvMain.setImageResource(R.drawable.ic_main_normal);
        mTvMain.setTextColor(getResources().getColor(R.color.colorTxtNormal));
        mIvLocation.setImageResource(R.drawable.ic_location_normal);
        mTvLocation.setTextColor(getResources().getColor(R.color.colorTxtNormal));
        mIvHotel.setImageResource(R.drawable.ic_hotel_normal);
        mTvHotel.setTextColor(getResources().getColor(R.color.colorTxtNormal));
        mIvTrack.setImageResource(R.drawable.ic_track_normal);
        mTvTrack.setTextColor(getResources().getColor(R.color.colorTxtNormal));
        mIvMine.setImageResource(R.drawable.ic_mine_normal);
        mTvMine.setTextColor(getResources().getColor(R.color.colorTxtNormal));

    }
}
