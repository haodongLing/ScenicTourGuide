package com.haodong.scenictourguide.login;

import android.util.Log;
import android.view.View;

import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LauncherFragment extends MyFragment {
    private static final String TAG=LauncherFragment.class.getSimpleName();

    @BindView(R.id.btn_submit)
    Button mBtnEnter;
    @OnClick
    void onSubmitClick(){
        getFragmentManager().beginTransaction().add(R.id.fl_launcher_contain,new LoginFragment()).hide(this)
                .commit();
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_launcher;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

    }
}
