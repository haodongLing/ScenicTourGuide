package com.haodong.scenictourguide.common.data.local;

import android.content.Context;

/**
 * describe :
 * date on 2019/5/1
 * author linghailong
 * email 105354999@qq.com
 */
public class GuidePreference extends GuideBasePerference{
    private final String GUIDE_USER_NAME="guide_user_name";
    private final String GUIDE_USER_ACCOUNT="guide_user_account";
    private final String GUIDE_USER_PASSWORD="guide_user_password";
    public GuidePreference(Context pContext) {
        super(pContext);
    }
    public void onSaveUserName(String name){
        saveString(GUIDE_USER_NAME,name);
    }
    public String onReadUserName(){
        return readString(GUIDE_USER_NAME,"");
    }
    public void onSaveAccount(String account){
        saveString(GUIDE_USER_ACCOUNT,account);
    }
    public String onReadAccount(){
        return readString(GUIDE_USER_ACCOUNT,"");
    }
    public void onSavePassword(String password){
        saveString(GUIDE_USER_PASSWORD,password);
    }
    public String onReadPassword(){
        return readString(GUIDE_USER_PASSWORD,"");
    }




}
