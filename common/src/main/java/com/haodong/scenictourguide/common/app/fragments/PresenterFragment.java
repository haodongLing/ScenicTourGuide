package com.haodong.scenictourguide.common.app.fragments;

import android.content.Context;
import android.widget.Toast;

import com.haodong.scenictourguide.common.presenter.BaseContract;

/**
 * describe :
 * date on 2019/2/2
 * author linghailong
 * email 105354999@qq.com
 */
public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends MyFragment
        implements BaseContract.View<Presenter> {
    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
    }
    public abstract Presenter initPresenter();

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerLoading();
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        // View中赋值Presenter
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.destroy();
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

}
