package com.haodong.scenictourguide.location.data;

import com.haodong.scenictourguide.common.presenter.BaseContract;

public interface LocationContact {
    interface Presenter extends BaseContract.Presenter {
        void loadFirstPage(int page,String location);
        void loadMorePage(int i);
        void  onRefresh();
    }
    interface View extends BaseContract.View<LocationContact.Presenter>{
        void showData(ScenicBean scenicBean);
        void showNoMoreData();
    }
}
