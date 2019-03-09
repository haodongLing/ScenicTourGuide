package com.haodong.scenictourguide.common.net;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/3/9
 * author linghailong
 * email 105354999@qq.com
 */
public class QueryCityInfoUtils {
    private static List<CityInfo.ResultBean>sCityArrs;

    private QueryCityInfoUtils() {
    }

    private static class Holder {
        private static final QueryCityInfoUtils INSTANCE = new QueryCityInfoUtils();
    }

    public static QueryCityInfoUtils getInstance() {
        return Holder.INSTANCE;
    }


    public void insertCityInfo(Context context) {
        WeakReference<Context> contextRef = new WeakReference<>(context);
        StringBuilder newstringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = contextRef.get().getResources().getAssets().open("city.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String jsonLine;
            while ((jsonLine = reader.readLine()) != null) {
                newstringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = newstringBuilder.toString();
        CityInfo cityInfo = JSON.parseObject(result, CityInfo.class);
        sCityArrs=new ArrayList<>();
        sCityArrs=cityInfo.getResult();
        for (CityInfo.ResultBean resultBean:sCityArrs){
            Log.e("lhl", "insertCityInfo: sCityArrs-->"+resultBean.getCityName()+"  "+resultBean.getCityId()+" " +
                    ""+ resultBean.getProvinceId());
        }

    }

    public CityInfo.ResultBean queryCityBean(String cityName) {
        if (sCityArrs != null) {
            int size = sCityArrs.size();
            for (int i = 0; i < size; i++) {
                /*如果有返回*/
                if (sCityArrs.get(i).getCityName().equals(cityName)){
                    Log.e("lhl", "queryCityBean: " +cityName);
                    return sCityArrs.get(i);
                }
            }
        }
        /*如果没有查到，返回null*/
        return null;
    }

}
