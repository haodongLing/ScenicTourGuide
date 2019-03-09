package com.haodong.scenictourguide.common.net;

import android.util.Log;

/**
 * describe :
 * date on 2019/3/9
 * author linghailong
 * email 105354999@qq.com
 */
public class UrlUtils {
    private static final String TAG="UrlUtils";
    public static String getUrlByPageAndLocation(CityInfo.ResultBean cityBean, int page) {
        if (cityBean!=null)
        {
            int pid =Integer.parseInt(cityBean.getProvinceId());
            int cid = Integer.parseInt(cityBean.getCityId());


            Log.e("lhl", "getUrlByPageAndLocation: pid--->"+pid+"cid---->"+cid );
            return "http://apis.haoservice" +
                    ".com/lifeservice/travel/scenery?pid="+pid+"&cid="+cid+"&page="+page+"&key" +
                    "=7040bc83a04d4de382b61d63a3edda19";
        }
        else {
            Log.e(TAG, "getUrlByPageAndLocation: cityBean is null");
            return "http://apis.haoservice" +
                    ".com/lifeservice/travel/scenery?pid=2&cid=45&page=1&key=7040bc83a04d4de382b61d63a3edda19";

        }


    }
}
