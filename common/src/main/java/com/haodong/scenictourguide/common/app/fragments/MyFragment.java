package com.haodong.scenictourguide.common.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.haodong.scenictourguide.common.widget.convention.PlaceHolderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public abstract class MyFragment extends Fragment implements ISupportFragment {
    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
    private View mRootView = null;
    protected Unbinder mRootUnBinder;
    protected PlaceHolderView mPlaceHolderView;
    // 标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;

    public abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mRootView = rootView;
        initWidget(mRootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitData) {
            // 触发一次以后就不会触发
            mIsFirstInitData = false;
            // 触发
            onFirstInit();
        }

        // 当View创建完成后初始化数据
        initData();
    }

    /**
     * 初始化相关参数
     */
    protected void initArgs(Bundle bundle) {

    }

    /**
     * 初始化控件
     */
    protected void initWidget(View root) {
        mRootUnBinder = ButterKnife.bind(this, root);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 当首次初始化数据的时候会调用的方法
     */
    protected void onFirstInit() {

    }

    /**
     * 返回按键触发时调用
     *
     * @return 返回True代表我已处理返回逻辑，Activity不用自己finish。
     * 返回False代表我没有处理逻辑，Activity自己走自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }


    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }

    @Override
    public void post(Runnable runnable) {
        DELEGATE.post(runnable);
    }

    protected FragmentActivity _mActivity;

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
        DELEGATE.onAttach((Activity) context);
        _mActivity = DELEGATE.getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return DELEGATE.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DELEGATE.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        DELEGATE.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        DELEGATE.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        DELEGATE.onPause();
    }

    @Override
    public void onDestroyView() {
        DELEGATE.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mRootUnBinder.unbind();
        DELEGATE.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        DELEGATE.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DELEGATE.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void enqueueAction(Runnable runnable) {
        DELEGATE.enqueueAction(runnable);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        DELEGATE.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {
        DELEGATE.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        DELEGATE.onSupportInvisible();
    }

    @Override
    final public boolean isSupportVisible() {
        return DELEGATE.isSupportVisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public boolean onBackPressedSupport() {
        return DELEGATE.onBackPressedSupport();
    }


    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle);
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data);
    }


    @Override
    public void onNewBundle(Bundle args) {
        DELEGATE.onNewBundle(args);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        DELEGATE.putNewBundle(newBundle);
    }

    public void start(ISupportFragment toFragment) {
        DELEGATE.start(toFragment);
    }

    public void start(final ISupportFragment toFragment, @LaunchMode int launchMode) {
        DELEGATE.start(toFragment, launchMode);
    }
}
