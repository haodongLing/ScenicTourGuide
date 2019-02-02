package com.haodong.scenictourguide.login;

import android.text.TextUtils;

import com.haodong.scenictourguide.common.Presenter.BasePresenter;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public class LoginPresenter extends BasePresenter<LoginContact.View> implements LoginContact
        .Presenter {

    LoginPresenter(LoginContact.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {
        start();
        final LoginContact.View view=getView();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            view.showError("填入的数字不能为空");
        } else {
            // 尝试传递PushId
           view.loginSuccess();
        }

    }
}
