package com.haodong.scenictourguide.common.ui;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

/**
 * describe: 模仿汽车之家的垂直下拉
 * created at 2019/2/13
 * Author linghailong
 */
public class MyVerticalScrollView extends FrameLayout {
    // 可以认为这是系统给我们写好的一个工具类
    private ViewDragHelper mDragHelper;

    private View mDragListView;
    // 后面菜单的高度
    private int mMenuHeight;
    // 菜单是否打开
    private boolean mMenuIsOpen = false;

    public MyVerticalScrollView(Context context) {
        this(context, null);
    }

    public MyVerticalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View menuView = getChildAt(0);
        mMenuHeight = menuView.getMeasuredHeight();
    }*/

    /*@Override
    public void addView(View child) {
        super.addView(child);
    }*/

    /*@Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }*/

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            View menuView = getChildAt(0);
            mMenuHeight = menuView.getMeasuredHeight();
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("VerticalDragListView 只能包含两个子布局");
        }

        mDragListView = getChildAt(1);
        mDragListView.scrollTo(0,mMenuHeight);

        /*View menuView = getChildAt(0);
        mMenuHeight = menuView.getMeasuredHeight();*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        // Log.e("TAG", "onTouchEvent -> " + event.getAction());
        return true;
    }

    // 1.拖动我们的子View
    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 指定该子View是否可以拖动，就是 child
            // 只能是列表可以拖动
            // 2.1 后面不能拖动
            return mDragListView == child;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // 垂直拖动移动的位置
            // 2.3 垂直拖动的范围只能是后面菜单 View 的高度
            if (top <= 0) {
                top = 0;
            }

            if (top >= mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }


        // 2.2 列表只能垂直拖动
        /*@Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // 水平拖动移动的位置
            return left;
        }*/

        // 2.4 手指松开的时候两者选其一，要么打开要么关闭
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // Log.e("TAG", "yvel -> " + yvel + " mMenuHeight -> " + mMenuHeight);
            // Log.e("TAG", "top -> " + mDragListView.getTop());
            if (releasedChild == mDragListView) {
                if (mDragListView.getTop() > mMenuHeight / 2) {
                    // 滚动到菜单的高度（打开）
                    mDragHelper.settleCapturedViewAt(0, mMenuHeight);
                    mMenuIsOpen = true;
                } else {
                    // 滚动到0的位置（关闭）
                    mDragHelper.settleCapturedViewAt(0, 0);
                    mMenuIsOpen = false;
                }
                invalidate();
            }
        }
    };

    // 现象就是ListView可以滑动，但是菜单滑动没有效果了
    private float mDownY;

    // ecause ACTION_DOWN was not received for this pointer before ACTION_MOVE
    // VDLV.onInterceptTouchEvent().DOWN -> LV.onTouch() ->
    // VDLV.onInterceptTouchEvent().MOVE -> VDLV.onTouchEvent().MOVE

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 菜单打开要拦截
        if (mMenuIsOpen) {
            return true;
        }

        // 向下滑动拦截，不要给ListView做处理
        // 谁拦截谁 父View拦截子View ，但是子 View 可以调这个方法
        // requestDisallowInterceptTouchEvent 请求父View不要拦截，改变的其实就是 mGroupFlags 的值
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                // 让 DragHelper 拿一个完整的事件
                mDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                if ((moveY - mDownY) > 0 && !canChildScrollUp()) {
                    // 向下滑动 && 滚动到了顶部，拦截不让ListView做处理
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     * 判断View是否滚动到了最顶部,还能不能向上滚
     */
    public boolean canChildScrollUp() {
        return ViewCompat.canScrollVertically(mDragListView, -1);
    }

    /**
     * 响应滚动
     */
    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
