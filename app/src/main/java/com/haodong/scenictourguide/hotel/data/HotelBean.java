package com.haodong.scenictourguide.hotel.data;

import java.util.List;

/**
 * describe :
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelBean {
    private List<HotelList>hotelLists;

    public List<HotelList> getHotelLists() {
        return hotelLists;
    }

    public void setHotelLists(List<HotelList> hotelLists) {
        this.hotelLists = hotelLists;
    }

    public static class HotelList{
        /**
         * englishName :
         * hotelId : 2318098
         * longitude : 116.417321
         * facilities : []
         * address : 东四南大街33号
         * latitude : 39.922239
         * price : 360
         * chineseName : 海友良品酒店(北京东四地铁站店)(原东四店)
         * star : 6
         * picture : http://m.tuniucdn.com/filebroker/cdn/res/5c/6b/5c6b09cdf27ae3cf1ad85a3d50e72e7f.jpg
         * starName : 经济型
         */
        private String englishName;
        private int hotelId;
        private double longitude;
        private String address;
        private double latitude;
        private int price;
        private String chineseName;
        private int star;
        private String picture;
        private String starName;
        private List<?> facilities;

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public int getHotelId() {
            return hotelId;
        }

        public void setHotelId(int hotelId) {
            this.hotelId = hotelId;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getChineseName() {
            return chineseName;
        }

        public void setChineseName(String chineseName) {
            this.chineseName = chineseName;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getStarName() {
            return starName;
        }

        public void setStarName(String starName) {
            this.starName = starName;
        }

        public List<?> getFacilities() {
            return facilities;
        }

        public void setFacilities(List<?> facilities) {
            this.facilities = facilities;
        }
    }

}
