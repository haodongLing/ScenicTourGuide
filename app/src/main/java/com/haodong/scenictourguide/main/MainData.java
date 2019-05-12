package com.haodong.scenictourguide.main;

import java.util.List;

/**
 * describe :
 * date on 2019/4/30
 * author linghailong
 * email 105354999@qq.com
 */
public class MainData {
    private List<MainDataInfo> mainDataInfos;

    public List<MainDataInfo> getMainDataInfos() {
        return mainDataInfos;
    }

    public void setMainDataInfos(List<MainDataInfo> mainDataInfos) {
        this.mainDataInfos = mainDataInfos;
    }

    public  static class MainDataInfo{
        private String portrait;
        private String name;
        private String location;
        private List<String>picUrlList;
        private String content;
        private String title;
        private String date;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public List<String> getPicUrlList() {
            return picUrlList;
        }

        public void setPicUrlList(List<String> picUrlList) {
            this.picUrlList = picUrlList;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }


}
