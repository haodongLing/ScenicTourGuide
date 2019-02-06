package com.haodong.scenictourguide.common.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * describe :
 * date on 2019/2/6
 * author linghailong
 * email 105354999@qq.com
 */

/**
 *
 */
public class AppUtils {
    public static void setInmmersiveStatusBar(Activity activity) {
        WeakReference<Activity> reference = new WeakReference<>(activity);
        Activity activity1 = reference.get();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity1.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity1.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public static void setInmmersiveStatusBar(Activity activity, String color) {
        WeakReference<Activity> reference = new WeakReference<>(activity);
        Activity activity1 = reference.get();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity1.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity1.getWindow().setStatusBarColor(Color.parseColor(color));
        }

    }
}
