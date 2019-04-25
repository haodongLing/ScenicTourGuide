package com.haodong.scenictourguide.hotel.contract;

import com.haodong.scenictourguide.common.presenter.BaseContract;
import com.haodong.scenictourguide.hotel.data.HotelBean;

/**
 * describe :
 * date on 2019/4/24
 * author linghailong
 * email 105354999@qq.com
 */
public interface HotelContract {
    interface Presenter extends BaseContract.Presenter{
        void loadFirstPage(String location);

    }
    interface View extends BaseContract.View<HotelContract.Presenter>{
        void showData(HotelBean hotelBean);
    }
}
