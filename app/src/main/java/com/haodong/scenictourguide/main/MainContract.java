package com.haodong.scenictourguide.main;

import com.haodong.scenictourguide.common.presenter.BaseContract;

/**
 * describe :
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public interface MainContract {
    interface Presenter extends BaseContract.Presenter{
        void loadData();

    }
    interface View extends BaseContract.View<MainContract.Presenter>{
        void initAdapter(MainData mainData);
    }
}
