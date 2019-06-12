package com.haodong.scenictourguide.track;

import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.main.MainData;
import com.haodong.scenictourguide.main.MainDataManager;

/**
 * describe :
 * date on 2019/6/12
 * author linghailong
 * email 105354999@qq.com
 */
public class UploadRunnable implements Runnable {
    private OnUploadFinishListener onUploadFinishListener;
    private MainData.MainDataInfo mainDataInfo;

    public UploadRunnable(MainData.MainDataInfo mainDataInfo, OnUploadFinishListener onUploadFinishListener) {
        this.onUploadFinishListener = onUploadFinishListener;
        this.mainDataInfo = mainDataInfo;
    }

    @Override
    public void run() {
        MainDataManager.getDefault().setData(mainDataInfo);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (onUploadFinishListener != null) {
            onUploadFinishListener.onFinish();
        }
    }

    public interface OnUploadFinishListener {
        void onFinish();
    }
}
