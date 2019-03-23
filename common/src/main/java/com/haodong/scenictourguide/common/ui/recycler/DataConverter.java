package com.haodong.scenictourguide.common.ui.recycler;

import java.util.ArrayList;

/**
 * describe :
 * date on 2019/3/23
 * author linghailong
 * email 105354999@qq.com
 */
public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public void clearData(){
        ENTITIES.clear();
    }
}
