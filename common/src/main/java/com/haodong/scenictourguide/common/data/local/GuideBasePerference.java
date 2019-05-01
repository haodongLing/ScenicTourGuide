package com.haodong.scenictourguide.common.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * describe :
 * date on 2019/5/1
 * author linghailong
 * email 105354999@qq.com
 */
public class GuideBasePerference {

    private final String SP_NAME = "tour_guide_preference";

    private Context mContext;
    private SharedPreferences mPreference;

    public GuideBasePerference(Context pContext) {
        mPreference = pContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    protected void saveBoolean(String pKey, boolean pValue) {
        mPreference.edit().putBoolean(pKey, pValue).apply();
    }

    protected boolean readBoolean(String pKey, boolean pDefaultValue) {
        return mPreference.getBoolean(pKey, pDefaultValue);
    }

    protected void saveInt(String pKey, int pValue) {
        mPreference.edit().putInt(pKey, pValue).apply();
    }

    protected int readInt(String pKey, int pDefaultValue) {
        return mPreference.getInt(pKey, pDefaultValue);
    }

    protected void saveFloat(String pKey, float pValue) {
        mPreference.edit().putFloat(pKey, pValue).apply();
    }

    protected float readFloat(String pKey, float pDefaultValue) {
        return mPreference.getFloat(pKey, pDefaultValue);
    }

    protected void saveString(String pKey, String pValue) {
        mPreference.edit().putString(pKey, pValue).apply();
    }

    protected String readString(String pKey, String pDefaultValue) {
        return mPreference.getString(pKey, pDefaultValue);
    }

    protected void saveLong(String pKey, long pValue) {
        mPreference.edit().putLong(pKey, pValue).apply();
    }

    protected long readLong(String pKey, long pDefaultValue) {
        return mPreference.getLong(pKey, pDefaultValue);
    }

}
