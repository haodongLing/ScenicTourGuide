package com.haodong.scenictourguide.location.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.haodong.scenictourguide.common.ui.recycler.DataConverter;
import com.haodong.scenictourguide.common.ui.recycler.MultipleFields;
import com.haodong.scenictourguide.common.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * describe :
 * date on 2019/3/24
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        MultipleItemEntity entity=MultipleItemEntity.builder().build();
        final JSONObject res_body=JSON.parseObject("showapi_res_body");
        final JSONObject pageBean= res_body.getJSONObject("pagebean");
        final int allPages=pageBean.getInteger("allPages");
        final JSONArray contentlist=pageBean.getJSONArray("contentlist");
        final int size=contentlist.size();
        for (int i=0;i<size;i++){
           final JSONObject data=contentlist.getJSONObject(i);
           final String summery=data.getString("summary");
            if (!summery.equals("")){
                entity.setField(MultipleFields.SUMMARY,summery);
            }

        }



        return ENTITIES;
    }

}
