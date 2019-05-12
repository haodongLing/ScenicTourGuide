package com.haodong.scenictourguide.login;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.haodong.scenictourguide.MainActivity;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.GuideManager;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;

import net.qiujuer.genius.ui.widget.Button;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public class LoginFragment extends PresenterFragment<LoginContact.Presenter> implements
        LoginContact.View {
    @BindView(R.id.edit_phone)
    EditText mEtvPhone;
    @BindView(R.id.edit_password)
    EditText mEtvPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @OnClick(R.id.btn_login)
    void onLoginClick(){
        String phone=mEtvPhone.getText().toString().trim();
        String pwd=mEtvPwd.getText().toString().trim();
        mPresenter.login(phone,pwd);
    }

    @Override
    protected void initData() {
        super.initData();
        String account=GuideManager.getDefault().onReadAccount();
        GuideManager.getDefault().onSaveUserName("呼啦啦");
        String password=GuideManager.getDefault().onReadPassword();
        if (account!=null&&!account.equals("")){
            mEtvPhone.setText(account);
        }
        if (password!=null&&!account.equals("")){
            mEtvPwd.setText(password);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_login;

    }
    @Override
    public void loginSuccess() {
        startActivity(new Intent(getContext(), MainActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }
    @Override
    public LoginContact.Presenter initPresenter() {
        return new LoginPresenter(this);
    }
}
