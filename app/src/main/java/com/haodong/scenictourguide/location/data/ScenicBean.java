package com.haodong.scenictourguide.location.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenicBean {
            private int allPages;
            private int currentPage;
            private int allNum;
            private int maxResult;
            private List<ContentlistBean> contentlist;

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean {
                private String id;
                private String proId;
                private String proName;
                private String cityId;
                private String cityName;
                private String summary;
                private String address;
                private String name;
                private String price;
                private String content;
                private String opentime;
                private String attention;
                private List<PicListBean> picList;
                private String lat;
                private String lon;

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLon() {
                    return lon;
                }

                public void setLon(String lon) {
                    this.lon = lon;
                }
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProId() {
                    return proId;
                }

                public void setProId(String proId) {
                    this.proId = proId;
                }

                public String getProName() {
                    return proName;
                }

                public void setProName(String proName) {
                    this.proName = proName;
                }

                public String getCityId() {
                    return cityId;
                }

                public void setCityId(String cityId) {
                    this.cityId = cityId;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }


                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getOpentime() {
                    return opentime;
                }

                public void setOpentime(String opentime) {
                    this.opentime = opentime;
                }

                public String getAttention() {
                    return attention;
                }

                public void setAttention(String attention) {
                    this.attention = attention;
                }


                public List<PicListBean> getPicList() {
                    return picList;
                }

                public void setPicList(List<PicListBean> picList) {
                    this.picList = picList;
                }
                public static class PicListBean {
                    /**
                     * picUrl : http://pic3.40017.cn/scenery/destination/2015/04/18/01/hwHxnP.jpg
                     * picUrlSmall : http://pic3.40017.cn/scenery/destination/2015/04/18/01/hwHxnP_130x130_00.jpg
                     */

                    private String picUrl;
                    private String picUrlSmall;

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getPicUrlSmall() {
                        return picUrlSmall;
                    }

                    public void setPicUrlSmall(String picUrlSmall) {
                        this.picUrlSmall = picUrlSmall;
                    }
                }
            }
}
