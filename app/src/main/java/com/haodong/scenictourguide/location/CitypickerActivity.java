package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import butterknife.BindView;
import butterknife.OnClick;

public class CitypickerActivity extends MyActivity {

    @BindView(R.id.btn_city_picker_jump)
    Button mBtnJump;

    @OnClick(R.id.btn_city_picker_jump)
    void onJumpClick() {
        Intent intent = getIntent();
        String location = "锦州";
        intent.putExtra("location", location);
        setResult(MyResultCode.RESULT_CODE_LOCATION, intent);
        finish();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_citypicker;
    }

    @Override
    protected void initData() {
        int anim = R.style.CustomAnim;
        CityPicker.from(CitypickerActivity.this)
                .enableAnimation(true)
                .setAnimationStyle(anim)
                .setLocatedCity(null)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        TourGuide.setConfiguration(ConfigKeys.CITY, data.getName());
                        TourGuide.setConfiguration(ConfigKeys.PROVINCE, data.getProvince());
                        Intent intent = getIntent();
                        String location = "锦州";
                        intent.putExtra("location", location);
                        setResult(MyResultCode.RESULT_CODE_LOCATION, intent);
                        finish();
                    }

                    @Override
                    public void onLocate() {
                        Handler handler = TourGuide.getConfiguration(ConfigKeys.HANDLER);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //定位完成之后更新数据
                                CityPicker.from(CitypickerActivity.this).locateComplete(new
                                        LocatedCity("北京", "北京", "101010100"), LocateState.SUCCESS);
                            }
                        }, 2000);

                    }

                    @Override
                    public void onCancel() {
                        finish();
                    }
                }).show();
    }
}
