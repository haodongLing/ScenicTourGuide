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
public class ProgressDialog extends Dialog {
    private Context mContext;
    private Button btnNext;
    private ImageView ivDelete;
    private LinearLayout mRootView;
    private int currentPage = 0;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        ButterKnife.bind(this);
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
//        getWindow().setAttributes(lp);
        this.getWindow().setGravity(Gravity.CENTER);
        this.setCanceledOnTouchOutside(true);
        this.getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
