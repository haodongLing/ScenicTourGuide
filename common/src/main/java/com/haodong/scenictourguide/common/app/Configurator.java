package com.haodong.scenictourguide.common.app;

import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public final class Configurator {
    private static final HashMap<Object, Object> GUIDE_CONFIGS = new HashMap<>();
    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 构造函数：实例化Handler
     */
    private Configurator() {
        GUIDE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        GUIDE_CONFIGS.put(ConfigKeys.HANDLER, sHandler);
    }
    /**
     * ConfiguratorHolder
     */
    private static class ConfiguratorHolder {
        private static final Configurator sConfigurator = new Configurator();
    }
    /**
     * 获取单例
     * @return
     */
    static Configurator getInstance() {
        return ConfiguratorHolder.sConfigurator;
    }
    public final void configure() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        GUIDE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Utils.init(TourGuide.getApplicationContext());
    }

    /**
     * 为loader配置时间
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed) {
        GUIDE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }
    /**
     * 为APPKEY
     * @param appKey
     * @return
     */
    public final Configurator withAppKey(String appKey) {
        GUIDE_CONFIGS.put(ConfigKeys.APPKEY, appKey);
        return this;
    }

    /**
     * 获取配置信息
     * @return
     */
    final HashMap<Object,Object>getGuideConfigs(){
        return GUIDE_CONFIGS;
    }


    public final Configurator withApiHost(String host) {
        GUIDE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }


    /**
     * 检查是否配置完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) GUIDE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = GUIDE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) GUIDE_CONFIGS.get(key);
    }
    final void setConfiguration(ConfigKeys key,Object value) {
        checkConfiguration();
        GUIDE_CONFIGS.put(key,value);
    }



}
