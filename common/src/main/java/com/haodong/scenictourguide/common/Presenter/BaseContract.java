package com.haodong.scenictourguide.common.Presenter;

import android.support.annotation.StringRes;

/**
 * describe : mvp基本的接口架构
 * author linghailong
 * date on 2019/1/26
 * email 105354999@qq.com
 */
public interface BaseContract {
    // 基本的界面职责
    interface View<T extends Presenter> {
        // 公共的：显示一个字符串错误
        void showError(String error);

        // 公共的：显示进度条
        void showLoading();

        // 支持设置一个Presenter
        void setPresenter(T presenter);
    }

    // 基本的Presenter职责
    interface Presenter {
        // 共用的开始触发
        void start();

        // 共用的销毁触发
        void destroy();
    }


}
