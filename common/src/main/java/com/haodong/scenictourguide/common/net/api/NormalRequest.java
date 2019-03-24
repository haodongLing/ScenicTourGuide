package com.haodong.scenictourguide.common.net.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * describe :
 * date on 2019/3/24
 * author linghailong
 * email 105354999@qq.com
 */
public class NormalRequest {
    protected String charset="utf-8";  //出去时的编码
    protected String url;
    private String appSecret;
    protected Map<String,String> textMap=new HashMap<String, String>();
    public NormalRequest( String url) {
        this.url=url;
        //先设置一个默认的头
        String appSecret = "a4c235776c8443dc88d53689b6ba4dc4";
        this.appSecret = appSecret;
        String appid = "89042";
        this.addTextPara("showapi_appid", appid);

    }
    public Map<String, String> getTextMap() {
        return textMap;
    }
    public void setTextMap(Map<String, String> textMap) {
        this.textMap = textMap;
    }
    /**
     * 添加post体的字符串参数
     */
    public NormalRequest addTextPara(String key,String value) {
        this.textMap.put(key,value);
        return this;
    }
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


    public static String buildQuery(Map  params)   {
        String charset="utf-8";
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        Set<Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;
        try {
            for (Entry<String, String> entry : entries) {
                String name = entry.getKey();
                String value = entry.getValue();
                // 忽略参数名或参数值为空的参数
                if(name!=null&&name.length()>0&&value!=null){
                    if (hasParam) {
                        query.append("&");
                    } else {
                        hasParam = true;
                    }
                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }

        return query.toString();
    }
    private String addSign() throws IOException {
        if (textMap.get(Constants.SHOWAPI_APPID) == null)
            return errorMsg(Constants.SHOWAPI_APPID + "不得为空!");
        textMap.put(Constants.SHOWAPI_SIGN, ShowApiUtils.signRequest(textMap, appSecret));
        return null;
    }

    public String getUrlString() {
        String str = null;
        String signResult = null;
        try {
            signResult = addSign();
            if (signResult != null)
                throw new NullPointerException("Constants.SHOWAPI_APPID) == null");
            str = buildQuery(this.textMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String errorMsg(String msg) {
        String str = "{" + Constants.SHOWAPI_RES_CODE + ":-1," + Constants.SHOWAPI_RES_ERROR + ":\"" + msg + "\"," +
                Constants.SHOWAPI_RES_BODY + ":{}}";
        return str;
    }


}
