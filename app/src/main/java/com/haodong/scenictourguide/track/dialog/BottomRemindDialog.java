package com.haodong.scenictourguide.track.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.haodong.scenictourguide.R;

import java.lang.ref.WeakReference;


/**
 * describe :
 * date on 2019/4/6
 * author linghailong
 * email 105354999@qq.com
 */
public class BottomRemindDialog extends Dialog implements View.OnClickListener{
    private Context mContext;
    private TextView tvSave,tvNot;
    private OnStateClickedLis mOnStateClickedLis;

    public BottomRemindDialog(@NonNull Context context, OnStateClickedLis onStateClickedLis) {
        super(context, R.style.BottomDialog);
        WeakReference<Context> contextRef=new WeakReference<>(context);
        this.mContext= contextRef.get();
        this.mOnStateClickedLis=onStateClickedLis;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remind_bottom);
        WindowManager windowManager=((Activity)mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width=mContext.getResources().getDisplayMetrics().widthPixels;
        getWindow().setAttributes(lp);
        this.getWindow().setGravity(Gravity.BOTTOM);
        this.setCanceledOnTouchOutside(true);
        this.getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
        tvSave=findViewById(R.id.bottom_dialog_save);
        tvSave.setOnClickListener(this);
        tvNot=findViewById(R.id.bottom_dialog_not_save);
        tvNot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int key=v.getId();
        if (key==R.id.bottom_dialog_save){
            mOnStateClickedLis.onSave();
            dismiss();
        }else if (key==R.id.bottom_dialog_not_save){
            mOnStateClickedLis.onCancel();
            dismiss();
        }

    }

    public interface OnStateClickedLis{
        void onSave();
        void onCancel();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
