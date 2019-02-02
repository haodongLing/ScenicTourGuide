package com.haodong.scenictourguide;

import android.app.Application;

import com.haodong.scenictourguide.common.app.TourGuide;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public class GuideApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TourGuide.init(this)
                .withLoaderDelayed(1500)
                .configure();
    }
}
