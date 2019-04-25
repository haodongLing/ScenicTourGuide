package com.haodong.scenictourguide.track;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;

import com.haodong.scenictourguide.MainActivity;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.common.presenter.BaseContract;
import com.haodong.scenictourguide.track.contractor.TrackContrator;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import java.util.Objects;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class TrackFragment extends PresenterFragment<TrackContrator.Presennter> implements TrackContrator.View,View.OnClickListener {
    private FloatActionButton mAction;

    @Override
    public Object setLayout() {
        return R.layout.fragment_track;
    }


    @Override
    public void setPresenter(TrackContrator.Presennter presenter) {

    }

    @Override
    public TrackPresenter initPresenter() {
        return new TrackPresenter(this);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mAction = root.findViewById(R.id.track_btn_action);
        mAction.setOnClickListener(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        float transY = 0;
        float rotation = 0;
        if (isVisibleToUser) {
            // 对浮动按钮进行隐藏与显示的动画
            // transY 默认为0 则显示
            rotation = 360;
        } else {
            transY = Ui.dipToPx(getResources(), -76);
        }
        // 开始动画
        // 旋转，Y轴位移，弹性差值器，时间
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.track_btn_action:
                startActivity(new Intent(getContext(),EditTrackActivity.class));
                break;
        }
    }
}
