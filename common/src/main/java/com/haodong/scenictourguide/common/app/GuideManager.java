package com.haodong.scenictourguide.common.app;

import android.app.Application;
import android.content.Context;

import com.haodong.scenictourguide.common.data.local.GuidePreference;

/**
 * describe :
 * date on 2019/5/1
 * author linghailong
 * email 105354999@qq.com
 */
public class GuideManager {
    public Context mContext;
    public GuidePreference mPerference;

    public static GuideManager getDefault() {
        return ManagerHolder.instance;
    }

    private static final class ManagerHolder {
        private static final GuideManager instance = new GuideManager();
    }

    public void init(Context pContext) {
        if (mContext != null) {
            /** 已经初始化 **/
            return;
        }

        if (pContext == null) {
            throw new RuntimeException("Param Context can not be null, init error!");
        }

        if (mContext instanceof Application) {
            mContext = pContext;
        } else {
            mContext = pContext.getApplicationContext();
        }
        if (mPerference == null) {
            mPerference = new GuidePreference(mContext);
        }
    }

    public void onSaveUserName(String name) {
        mPerference.onSaveUserName(name);
    }

    public String onReadUserName() {
        return mPerference.onReadUserName();
    }

    public void onSaveAccount(String account) {
        mPerference.onSaveAccount(account);
    }

    public String onReadAccount() {
        return mPerference.onReadAccount();
    }

    public void onSavePassword(String password) {
        mPerference.onSavePassword(password);
    }

    public String onReadPassword() {
        return mPerference.onReadPassword();
    }
}
