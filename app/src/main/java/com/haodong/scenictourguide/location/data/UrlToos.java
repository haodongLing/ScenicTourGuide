package com.haodong.scenictourguide.location.data;

import com.haodong.scenictourguide.common.net.api.NormalRequest;

/**
 * describe :
 * date on 2019/3/25
 * author linghailong
 * email 105354999@qq.com
 */
public class UrlToos {
    public static String getUrl(String location,String page){
        String url = new NormalRequest("http://route.showapi.com/268-1")
                .addTextPara("keyword",location)
                .addTextPara("page", page)
                .getUrlString();
        return "http://route.showapi.com/268-1?"+url;
    }
    public static String getHotelUrl(String location){
        String url = new NormalRequest("http://route.showapi.com/1653-1")
                .addTextPara("cityName",location)
                .getUrlString();
        return "http://route.showapi.com/1653-1?"+url;
    }
}
