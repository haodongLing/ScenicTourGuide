package com.haodong.scenictourguide.location.data;

import java.util.List;

public class ScenicBean {

    /**
     * error_code : 0
     * reason : 成功
     * result : [{"title":"西递","grade":"AAAAA","price_min":"94","comm_cnt":null,"cityId":"45",
     * "address":"安徽省黄山市黟县西递镇西递村","sid":"29001","url":"http://www.ly
     * .com/scenery/BookSceneryTicket_29001.html",
     * "imgurl":"http://pic4.40017.cn/scenery/destination/2016/10/10/11/2VGv1N_240x135_00.jpg"},
     * {"title":"齐云山（四大道教名山）","grade":"AAAA","price_min":"34","comm_cnt":null,"cityId":"45",
     * "address":"安徽省黄山市休宁县齐云山镇","sid":"1021","url":"http://www.ly
     * .com/scenery/BookSceneryTicket_1021.html",
     * "imgurl":"http://pic4.40017.cn/scenery/destination/2016/07/30/10/sPbd0B_240x135_00.jpg"},
     * {"title":"呈坎","grade":"AAAAA","price_min":"102","comm_cnt":null,"cityId":"45",
     * "address":"安徽黄山市歙县（黄山南面40公里处在灵山和丰山之间）","sid":"1510","url":"http://www.ly
     * .com/scenery/BookSceneryTicket_1510.html",
     * "imgurl":"http://pic4.40017.cn/scenery/destination/2017/02/23/13/MbAoEb_240x135_00.jpg"},
     * {"title":"黄山温泉","grade":"","price_min":"68","comm_cnt":null,"cityId":"45",
     * "address":"安徽省黄山市黄山风景区内黄山温泉景区","sid":"373","url":"http://www.ly
     * .com/scenery/BookSceneryTicket_373.html",
     * "imgurl":"http://pic4.40017.cn/scenery/destination/2016/09/27/22/JFl5Pp_240x135_00.jpg"},
     * {"title":"陶渊明故居守拙园","grade":"","price_min":"40","comm_cnt":null,"cityId":"45",
     * "address":"安徽省黄山市黟县碧阳镇陶村","sid":"212142","url":"http://www.ly
     * .com/scenery/BookSceneryTicket_212142.html",
     * "imgurl":"http://pic4.40017.cn/scenery/destination/2016/11/15/17/tV1JqQ_240x135_00.jpg"}]
     */

    private int error_code;
    private String reason;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : 西递
         * grade : AAAAA
         * price_min : 94
         * comm_cnt : null
         * cityId : 45
         * address : 安徽省黄山市黟县西递镇西递村
         * sid : 29001
         * url : http://www.ly.com/scenery/BookSceneryTicket_29001.html
         * imgurl : http://pic4.40017.cn/scenery/destination/2016/10/10/11/2VGv1N_240x135_00.jpg
         */

        private String title;
        private String grade;
        private String price_min;
        private Object comm_cnt;
        private String cityId;
        private String address;
        private String sid;
        private String url;
        private String imgurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getPrice_min() {
            return price_min;
        }

        public void setPrice_min(String price_min) {
            this.price_min = price_min;
        }

        public Object getComm_cnt() {
            return comm_cnt;
        }

        public void setComm_cnt(Object comm_cnt) {
            this.comm_cnt = comm_cnt;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
