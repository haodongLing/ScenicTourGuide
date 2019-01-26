package com.haodong.scenictourguide.common.Presenter;

/**
 * describe : basePresenter
 * author linghailong
 * date on 2019/1/26
 * email 105354999@qq.com
 */
public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {
    private T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个View，子类可以复写
     */
    @SuppressWarnings("unchecked")
    protected void setView(T view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    /**
     * 给子类使用的获取View的操作
     * 不允许复写
     *
     * @return View
     */
    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        // 开始的时候进行Loading调用
        T view = mView;
        if (isViewAttached()) {
            view.showLoading();
        }
    }
    public boolean isViewAttached() {
        return mView != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            // 把Presenter设置为NULL
            view.setPresenter(null);
        }
    }
}
