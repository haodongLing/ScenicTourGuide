package com.haodong.scenictourguide.main;

import com.squareup.haha.perflib.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/5/9
 * author linghailong
 * email 105354999@qq.com
 */
public class MainDataManager {
    private List<MainData.MainDataInfo>mainDatas=new ArrayList<>();
    private static final class Holder{
        private static final MainDataManager sInstance=new MainDataManager();
    }
    public static MainDataManager getDefault(){
        return Holder.sInstance;
    }
    public void setData(MainData.MainDataInfo mainData){
        mainDatas.add(0,mainData);
    }
    public void setDatas(List<MainData.MainDataInfo> mainDatas){
        mainDatas.addAll(mainDatas);
    }
    public List<MainData.MainDataInfo> getMainDatas(){
        return mainDatas;
    }
}
