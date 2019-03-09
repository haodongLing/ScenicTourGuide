package com.haodong.scenictourguide.location.data;

import com.haodong.scenictourguide.common.presenter.BaseContract;

public interface LocationContact {
    interface Presenter extends BaseContract.Presenter {
        void loadFirstPage();
        void loadMorePage(int i);
        void  onRefresh();
    }
}
