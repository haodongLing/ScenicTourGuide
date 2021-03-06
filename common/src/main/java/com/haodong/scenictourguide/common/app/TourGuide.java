package com.haodong.scenictourguide.common.app;

import android.app.Application;
import android.content.Context;
import android.print.PrinterId;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import com.haodong.scenictourguide.common.data.local.GuidePreference;

import java.util.logging.Handler;

/**
 * describe : 简单工厂
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public final class TourGuide {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getGuideConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
    public static void setConfiguration(ConfigKeys key,Object value) {
            getConfigurator().setConfiguration(key,value);
    }

    public static Application getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}
