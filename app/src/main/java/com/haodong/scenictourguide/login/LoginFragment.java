package com.haodong.scenictourguide.login;

import android.content.Intent;

import com.haodong.scenictourguide.MainActivity;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public class LoginFragment extends PresenterFragment<LoginContact.Presenter> implements
        LoginContact.View {
    @Override
    public Object setLayout() {
        return R.layout.fragment_login;

    }


    @Override
    public void loginSuccess() {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public LoginContact.Presenter initPresenter() {
        return new LoginPresenter(this);
    }
}
