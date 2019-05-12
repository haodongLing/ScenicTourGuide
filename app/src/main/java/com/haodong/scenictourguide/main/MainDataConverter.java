package com.haodong.scenictourguide.main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haodong.scenictourguide.common.ui.recycler.DataConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/4/30
 * author linghailong
 * email 105354999@qq.com
 */
public class MainDataConverter extends DataConverter<MainData> {
    @Override
    public MainData convert() {
//        MainData mainData = new MainData();
//        List<MainData.MainDataInfo> mainDataInfos = new ArrayList<>(20);
//        MainData.MainDataInfo mainDataInfo = new MainData.MainDataInfo();
//        mainDataInfo.setPortrait("http://ww1.sinaimg.cn/large/006fCF3Ply1g2jwqq4osgj303c03cjrf.jpg");
//        mainDataInfo.setTitle("北京逛故宫全技能Get！");
//        mainDataInfo.setContent("北京土著说：我逛不逛，它都在那儿，我想啥时候逛就啥时候逛！外地游客说：来到大北京，必须逛故宫！常住在北京的外地人说：老家人来了N次，故宫已经逛了N" +
//                "次！不管你是从没逛过，还是第一次逛，还是正要第N+1次逛，逛故宫的技能你都get了吗？");
//        mainDataInfo.setLocation("北京");
//        mainDataInfo.setDate("2018-04-07");
//        mainDataInfo.setName("京游阿哥");
//        List<String> picUrls = new ArrayList<>();
//        picUrls.add("http://ww1.sinaimg.cn/large/006fCF3Ply1g2jwtztbhtj311s0oiwpz.jpg");
//        picUrls.add("http://ww1.sinaimg.cn/large/006fCF3Ply1g2jwul844ej311s0p117z.jpg");
//        picUrls.add("http://ww1.sinaimg.cn/large/006fCF3Ply1g2jwusx29uj30m80eqdkt.jpg");
//        picUrls.add("http://ww1.sinaimg.cn/large/006fCF3Ply1g2jwv5frjzj311s0p846x.jpg");
//        mainDataInfo.setPicUrlList(picUrls);
//        mainDataInfos.add(mainDataInfo);
//        mainData.setMainDataInfos(mainDataInfos);
//        return mainData;
        MainData mainData=new MainData();
        List<MainData.MainDataInfo> mainDataInfos = new ArrayList<>(20);
        final JSONObject s=JSONObject.parseObject(getJsonData());
        final JSONArray scenic=s.getJSONArray("scenic");
        final  int size=scenic.size();
        for (int i=0;i<size;i++){
            MainData.MainDataInfo mainDataInfo = new MainData.MainDataInfo();
            List<String> picUrls = new ArrayList<>();
            final JSONObject data=scenic.getJSONObject(i);
            final String id=data.getString("id");
            final JSONArray picLists=s.getJSONArray(id);
            final int picSize=picLists.size();
            for (int j=0;j<picSize;j++){
                final JSONObject picInfo=picLists.getJSONObject(j);
                final String picUrl=picInfo.getString("picUrlList");
                picUrls.add(picUrl);
            }
            mainDataInfo.setPicUrlList(picUrls);
            final String portait=data.getString("portait");
            mainDataInfo.setPortrait(portait);
            final String name=data.getString("name");
            mainDataInfo.setName(name);
            final String title=data.getString("title");
            mainDataInfo.setTitle(title);
            final String content=data.getString("content");
            mainDataInfo.setContent(content);
            final String date=data.getString("date");
            mainDataInfo.setDate(date);
            final String location=data.getString("location");
            mainDataInfo.setLocation(location);
            mainDataInfos.add(mainDataInfo);
            if (mainDataInfo.getName().contains("呼啦啦")){
                MainDataManager.getDefault().setData(mainDataInfo);
            }
        }
        mainData.setMainDataInfos(mainDataInfos);
        return mainData;
    }
}
