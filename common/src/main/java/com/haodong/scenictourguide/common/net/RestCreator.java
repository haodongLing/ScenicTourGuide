package com.haodong.scenictourguide.common.net;

import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.net.cache.CacheIntercepter;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public class RestCreator {
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final int CACHE_SIZE = 20 * 1024 * 1024;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
//                .addNetworkInterceptor(new CacheIntercepter())
                .cache(new Cache(TourGuide.getApplicationContext().getCacheDir(), CACHE_SIZE))
                .build();
    }

    /*单例模式的静态颞部类*/
    private static final class RetrofitHolder {

        /*baseAPI*/
//        private static final String BASE_URL = TourGuide.getConfiguration(ConfigKeys.API_HOST);

        /*建造者模式*/
        /*添加转换器*/
        private static final String BASE_URL = "http://www.baidu.com";
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }

}
