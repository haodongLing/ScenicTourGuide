package com.haodong.scenictourguide;

import com.haodong.scenictourguide.location.data.ScenicBean;

/**
 * describe :数据暂存类
 * date on 2019/4/13
 * author linghailong
 * email 105354999@qq.com
 */
public class DataTools {
    private ScenicBean.ContentlistBean mContentListBean;
    private static class Holder{
        private static final DataTools HOLDER=new DataTools();
    }
    public static DataTools getInstance(){
        return Holder.HOLDER;
    }

    public ScenicBean.ContentlistBean getContentListBean() {
        return mContentListBean;
    }

    public void setContentListBean(ScenicBean.ContentlistBean contentListBean) {
        this.mContentListBean = contentListBean;
    }
}
