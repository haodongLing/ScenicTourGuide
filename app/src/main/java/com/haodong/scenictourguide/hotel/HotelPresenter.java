package com.haodong.scenictourguide.hotel;

import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.hotel.contract.HotelContract;

/**
 * describe :
 * date on 2019/4/24
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelPresenter extends BasePresenter<HotelFragment>implements HotelContract.Presenter {
    public HotelPresenter(HotelFragment view) {
        super(view);
    }
}
