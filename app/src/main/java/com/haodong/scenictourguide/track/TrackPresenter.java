package com.haodong.scenictourguide.track;

import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.track.contractor.TrackContrator;

/**
 * describe :
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public class TrackPresenter extends BasePresenter<TrackFragment> implements TrackContrator.Presennter {
    public TrackPresenter(TrackFragment view) {
        super(view);
    }
}
