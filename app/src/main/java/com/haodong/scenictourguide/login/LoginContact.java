package com.haodong.scenictourguide.login;

import com.haodong.scenictourguide.common.presenter.BaseContract;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public interface LoginContact  {
    interface View extends BaseContract.View<Presenter> {
        // 登录成功
        void loginSuccess();
        void loginError();
    }


    interface Presenter extends BaseContract.Presenter {
        // 发起一个登录
        void login(String phone, String password);
    }
}
