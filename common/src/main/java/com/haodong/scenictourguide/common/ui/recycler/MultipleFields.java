package com.haodong.scenictourguide.common.ui.recycler;

import android.print.PrinterId;

/**
 * describe : 景区信息的数据结构类型
 * date on 2019/3/23
 * author linghailong
 * email 105354999@qq.com
 */
public enum MultipleFields {

    //    /*详细信息总结*/
//    private static final int SUMMARY=0x001;
//    /*详细地址*/
//    private static final int ADDRESS=0X002;
//
//    private static final int NAME=0X003;
//    /*价格*/
//    private static final int PRICE=0x004;
//    /*详细信息*/
//    private static final int CONTENT=0x005;
//
//    private static final int PICS_URL=0x006;
//    private static final int PICS_SMALL=0x007;
//
//    private static final int OPENTIME=0X008;
//    /*经纬度*/
//    private static final int LAT=0X009;
//
//    private static final int LON=0x00a;
//    /*注意事项*/
//    private static final int ATTENTION=0x00b;
    ITEM_TYPE,
    SUMMARY,
    ADDRESS,
    NAME,
    PRICE,
    CONTENT,
    PICS_URL,
    PICS_SMALL,
    OPENTIME,
    LAT,
    LON,
    ATTENTION,
    ALL_PAGES

}
