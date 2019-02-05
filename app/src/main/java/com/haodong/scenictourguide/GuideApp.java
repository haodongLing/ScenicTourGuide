package com.haodong.scenictourguide;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.service.LocationService;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public class GuideApp extends Application {
    /*定位相关*/
    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        TourGuide.init(this)
                .withLoaderDelayed(1500)
                .configure();
        /***
         * 初始化定位sdk，
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }
}
