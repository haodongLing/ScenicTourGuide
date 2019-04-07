package com.haodong.scenictourguide.location;

import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.location.data.contractor.AttractionContract;

/**
 * describe :
 * date on 2019/4/7
 * author linghailong
 * email 105354999@qq.com
 */
public class AttractionPresenter extends BasePresenter<AttractionContract.View> implements AttractionContract
        .Presenter {

    public AttractionPresenter(AttractionContract.View view) {
        super(view);
    }
}
