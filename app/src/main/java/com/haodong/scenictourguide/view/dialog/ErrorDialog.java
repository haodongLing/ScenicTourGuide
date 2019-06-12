package com.haodong.scenictourguide.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haodong.scenictourguide.R;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.ButterKnife;


/**
 * describe :
 * date on 2019/4/6
 * author linghailong
 * email 105354999@qq.com
 */
public class ErrorDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private OnStateClickedLis mOnStateClickedLis;
    private Button btnNext;
    private ImageView ivDelete;
    private LinearLayout mRootView;
    private int currentPage = 0;
    private LinearLayout mViewYzm, mViewQueren;

    public ErrorDialog(@NonNull Context context, OnStateClickedLis onStateClickedLis) {
        super(context, R.style.dialog);
//        WeakReference<Context> contextRef = new WeakReference<>(context);
        this.mContext = context;
        this.mOnStateClickedLis = onStateClickedLis;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_error);
        ButterKnife.bind(this);
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
//        getWindow().setAttributes(lp);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setCanceledOnTouchOutside(true);
        this.getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
        ivDelete = findViewById(R.id.iv_delete);
        btnNext = findViewById(R.id.btn_next);
        ivDelete.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        if (key == R.id.iv_delete) {
            dismiss();
            mOnStateClickedLis.onCancel();
        } else if (key == R.id.btn_next) {
        }

    }

    public interface OnStateClickedLis {
        void onSave();

        void onCancel();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
