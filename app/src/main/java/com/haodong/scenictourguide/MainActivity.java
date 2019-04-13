package com.haodong.scenictourguide;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.utils.AppUtils;
import com.haodong.scenictourguide.hotel.HotelFragment;
import com.haodong.scenictourguide.location.LocationFragment;
import com.haodong.scenictourguide.main.MainFragment;
import com.haodong.scenictourguide.mine.MineFragment;
import com.haodong.scenictourguide.service.LocationService;
import com.haodong.scenictourguide.track.TrackFragment;

import butterknife.BindView;
import retrofit2.http.PUT;

public class MainActivity extends MyActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int MAIN = 0;
    public static final int LOCATION = 1;
    public static final int HOTEL = 2;
    public static final int TRACK = 3;
    public static final int MINE = 4;
    private int positin = MAIN;
    private int prePosition = 0;
    private Boolean isFirstIn = true;
    private MyFragment[] mFragments = new MyFragment[5];
    /*定位相关*/
    private LocationService locationService;

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
    protected void initWindows() {
        AppUtils.setInmmersiveStatusBar(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationService = ((GuideApp) getApplication()).locationService;
        /*注册监听*/
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
           /* String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息*/
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            if (isFirstIn) {
                if (province != null && city != null) {
                    Log.e("tag", "onReceiveLocation: " + city + province);
                    mTvLocation.setText(city);
                    TourGuide.setConfiguration(ConfigKeys.PROVINCE, province);

                    TourGuide.setConfiguration(ConfigKeys.CITY, city);
                    Log.e(TAG, "onReceiveLocation: " + TourGuide.getConfiguration(ConfigKeys
                            .PROVINCE)
                    );
                }
                isFirstIn = false;
            } else {
                mTvLocation.setText(TourGuide.getConfiguration(ConfigKeys.CITY));
            }

        }
    };

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
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
    protected void initData() {
        super.initData();
        MyFragment firstFragment = findFragment(MainFragment.class);
        if (firstFragment == null) {
            mFragments[MAIN] = new MainFragment();
            mFragments[LOCATION] = new LocationFragment();
            mFragments[HOTEL] = new HotelFragment();
            mFragments[TRACK] = new TrackFragment();
            mFragments[MINE] = new MineFragment();
            loadMultipleRootFragment(R.id.lay_container, MAIN, mFragments);

        } else {
            mFragments[MAIN] = firstFragment;
            mFragments[LOCATION] = findFragment(LocationFragment.class);
            mFragments[HOTEL] = findFragment(HotelFragment.class);
            mFragments[TRACK] = findFragment(TrackFragment.class);
            mFragments[MINE] = findFragment(MineFragment.class);
        }
    }

    @Override
    public void onClick(View v) {
        resetTabState();
        switch (v.getId()) {
            case R.id.tab_bottom_main:
                mIvMain.setImageResource(R.drawable.ic_main_color);
                mTvMain.setTextColor(Color.parseColor("#EE7C3D"));
                prePosition = positin;
                positin = MAIN;
                if (prePosition != positin) {
                    showHideFragment(mFragments[positin], mFragments[prePosition]);
                }
                break;
            case R.id.tab_bottom_location:
                mIvLocation.setImageResource(R.drawable.ic_location);
                mTvLocation.setTextColor(Color.parseColor("#EE7C3D"));
                prePosition = positin;
                positin = LOCATION;
                if (prePosition != positin) {
                    showHideFragment(mFragments[positin], mFragments[prePosition]);
                }
                break;
            case R.id.tab_bottom_hotel:
                mIvHotel.setImageResource(R.drawable.ic_hotel_color);
                mTvHotel.setTextColor(Color.parseColor("#EE7C3D"));
                prePosition = positin;
                positin = HOTEL;
                if (prePosition != positin) {
                    showHideFragment(mFragments[positin], mFragments[prePosition]);
                }
                break;
            case R.id.tab_bottom_track:
                mIvTrack.setImageResource(R.drawable.ic_track_color);
                mTvTrack.setTextColor(Color.parseColor("#EE7C3D"));
                prePosition = positin;
                positin = TRACK;
                if (prePosition != positin) {
                    showHideFragment(mFragments[positin], mFragments[prePosition]);
                }
                break;
            case R.id.tab_bottom_mine:
                mIvMine.setImageResource(R.drawable.ic_mine_color);
                mTvMine.setTextColor(Color.parseColor("#EE7C3D"));
                prePosition = positin;
                positin = MINE;
                if (prePosition != positin) {
                    showHideFragment(mFragments[positin], mFragments[prePosition]);
                }
                break;
            default:

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyRequestCode.REQUEST_CODE_LOCATION && resultCode == MyResultCode
                .RESULT_CODE_LOCATION) {
            mFragments[LOCATION].onActivityResult(requestCode, resultCode, data);
        }
    }
}
