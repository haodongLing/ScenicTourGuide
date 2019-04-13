package com.haodong.scenictourguide.location.data;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.haodong.scenictourguide.common.ui.recycler.DataConverter;
import com.haodong.scenictourguide.common.ui.recycler.MultipleFields;
import com.haodong.scenictourguide.common.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/3/24
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationDataConverter extends DataConverter<ScenicBean> {

    @Override
    public ScenicBean convert() {
        ScenicBean scenicBean=new ScenicBean();
        final JSONObject s = JSONObject.parseObject(getJsonData());
        final JSONObject res_body=s.getJSONObject("showapi_res_body");
        final JSONObject pageBean = res_body.getJSONObject("pagebean");
        final int allPages = pageBean.getInteger("allPages");
        Log.e("lhl", "convert: "+allPages);
        scenicBean.setAllPages(allPages);

        final JSONArray contentlist = pageBean.getJSONArray("contentlist");
        final int size = contentlist.size();
        List<ScenicBean.ContentlistBean>contentlistBeanList=new ArrayList<>();
        for (int i = 0; i < size; i++) {

            ScenicBean.ContentlistBean contentlistBean=new ScenicBean.ContentlistBean();
            final JSONObject data = contentlist.getJSONObject(i);
            /*1 summery*/
            final String summery = data.getString("summary");
            if (summery!=null&&!summery.equals("")) {
                contentlistBean.setSummary(summery);
            }
            /*2. 地址*/
            final String address=data.getString("address");
            if (address!=null&&!address.equals("")){
                contentlistBean.setAddress(address);
            }
            /*3. name*/
            final String name=data.getString("name");
            if (name!=null&&!name.equals("")){
                contentlistBean.setName(name);
            }

            final JSONObject location=data.getJSONObject("location");
            if (location!=null){
                final String lat=location.getString("lat");
                final String lon=location.getString("lon");
                if (!lat.equals("")&&!lon.equals("")){
                    contentlistBean.setLat(lat);
                    contentlistBean.setLon(lon);
                }
            }

            /*4. price*/
            final String price=data.getString("price");
            if (price!=null&&!price.equals("")){
                contentlistBean.setPrice("price");
            }
            /*5. content*/
            final String content=data.getString("content");
            if (content!=null&&!content.equals("")){
                contentlistBean.setContent(content);
            }
            /*6. attention*/
            final String attention=data.getString("attention");
            if (attention!=null&&!attention.equals("")){
                contentlistBean.setAttention(attention);
            }
            List<ScenicBean.ContentlistBean.PicListBean>picListBeans=new ArrayList<>();
            /*6. 图片URL*/
            final JSONArray picList=data.getJSONArray("picList");
            if (picList!=null){
                int picSize=picList.size();
                for (int j=0;j<picSize;j++){
                    ScenicBean.ContentlistBean.PicListBean picListBean=new ScenicBean.ContentlistBean.PicListBean();
                    final JSONObject object=picList.getJSONObject(j);
                    if (object!=null){
                        final String picUrl=object.getString("picUrl");
                        final String picUrlSmall=object.getString("picUrlSmall");
                        if (picUrl!=null){
                            picListBean.setPicUrl(picUrl);
                        }else {
                            picListBean.setPicUrlSmall(picUrlSmall);
                        }
                        picListBeans.add(picListBean);
                    }

                }
            }

            contentlistBean.setPicList(picListBeans);
            final String opentime=data.getString("opentime");
            if (opentime!=null&&!opentime.equals("")){
                contentlistBean.setOpentime(opentime);
            }
            contentlistBeanList.add(contentlistBean);
        }
        scenicBean.setContentlist(contentlistBeanList);
        Log.e("lhl", "convert: "+scenicBean );
        return scenicBean;
    }


}
