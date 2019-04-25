package com.haodong.scenictourguide.hotel.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haodong.scenictourguide.common.ui.recycler.DataConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelDataConverter extends DataConverter<HotelBean> {
    /**
     * address : 东四南大街33号
     * chineseName : 海友良品酒店(北京东四地铁站店)(原东四店)
     * star : 6
     * picture : http://m.tuniucdn.com/filebroker/cdn/res/5c/6b/5c6b09cdf27ae3cf1ad85a3d50e72e7f.jpg
     * starName : 经济型
     */
    @Override
    public HotelBean convert() {
        HotelBean hotelBean = new HotelBean();
        final JSONObject s = JSONObject.parseObject(getJsonData());
        final JSONObject res_body = s.getJSONObject("showapi_res_body");
        final JSONObject data = res_body.getJSONObject("data");
        final JSONArray hotelList = data.getJSONArray("hotelList");
        final int size = hotelList.size();
        List<HotelBean.HotelList> hotelLists = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HotelBean.HotelList hotel = new HotelBean.HotelList();
            final JSONObject hotelInfo = hotelList.getJSONObject(i);
            final String address = hotelInfo.getString("address");
            final String chineseName = hotelInfo.getString("chineseName");
            if (hotelInfo.getInteger("star") != null) {
                hotel.setStar(hotelInfo.getInteger("star"));
            }
            final String picture = hotelInfo.getString("picture");
            final String starName = hotelInfo.getString("starName");
            if (address != null) {
                hotel.setAddress(address);
            }
            if (chineseName != null) {
                hotel.setChineseName(chineseName);
            }
            if (picture != null) {
                hotel.setPicture(picture);
            }
            if (starName != null) {
                hotel.setStarName(starName);
            }
            hotelLists.add(hotel);
        }
        hotelBean.setHotelLists(hotelLists);
        return hotelBean;
    }
}
