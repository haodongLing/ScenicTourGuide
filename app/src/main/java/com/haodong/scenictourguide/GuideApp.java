package com.haodong.scenictourguide;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.GuideManager;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.data.local.GuidePreference;
import com.haodong.scenictourguide.common.net.QueryCityInfoUtils;
import com.haodong.scenictourguide.service.LocationService;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
    public static GuideApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        TourGuide.init(this)
                .withLoaderDelayed(1500)
                .withApiHost("http://route.showapi.com/268-1")
                .withAppKey("7040bc83a04d4de382b61d63a3edda19")
                .configure();
        GuideManager.getDefault().init(this);
        /***
         * 初始化定位sdk，
         */
        instance=this;
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        refWatcher= LeakCanary.install(this);
        TourGuide.setConfiguration(ConfigKeys.CITY,"锦州");
        TourGuide.setConfiguration(ConfigKeys.PROVINCE,"锦州");
    }
    RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        GuideApp application = (GuideApp) context.getApplicationContext();
        return application.refWatcher;
    }
    public void mustDie(Object object) {
        if (refWatcher != null) {
            refWatcher.watch(object);
        }
    }

}
