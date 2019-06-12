package com.haodong.scenictourguide.login;

import android.text.TextUtils;

import com.haodong.scenictourguide.common.app.GuideManager;
import com.haodong.scenictourguide.common.presenter.BasePresenter;

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
        final LoginContact.View view = getView();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            view.showError("填入的数字不能为空");
        } else {
            // 尝试传递PushId
            GuideManager.getDefault().onSaveAccount(phone);
            String pwd = GuideManager.getDefault().onReadPassword();
            if (!pwd.equals("") && password.equals(pwd)) {
                view.loginSuccess();
            } else if (pwd.equals("")) {
                GuideManager.getDefault().onSavePassword(password);
            } else {
                view.loginError();
            }
        }
    }
}
