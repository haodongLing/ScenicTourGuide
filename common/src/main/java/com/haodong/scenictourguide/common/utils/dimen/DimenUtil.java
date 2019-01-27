package com.haodong.scenictourguide.common.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.haodong.scenictourguide.common.app.TourGuide;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = TourGuide.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = TourGuide.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
