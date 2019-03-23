package com.haodong.scenictourguide.location.data;

import java.util.List;

public class ScenicBean {
    private String showapi_res_error;
    private String showapi_res_id;
    private int showapi_res_code;
    private ShowapiResBodyBean showapi_res_body;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {

        private int ret_code;
        private PagebeanBean pagebean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public static class PagebeanBean {

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
                /**
                 * id : 9290
                 * proId : 18
                 * proName : 辽宁
                 * cityId : 253
                 * cityName : 锦州
                 * areaId : 1788
                 * areaName : 锦州市区
                 * summary : 香火旺盛，融宗教与旅游为一体的著名风景区
                 * address : 辽宁省锦州北镇市常兴店镇青岩寺风景区
                 * name : 青岩寺(歪脖老母)
                 * location : {"lat":"41.47879736","lon":"121.62824592"}
                 * price : 35.00
                 * content :
                 * 歪脖老母位于辽宁省北镇市常兴店镇青岩寺。青岩寺始建于北魏，盛于中唐，至今有一千五百余年历史。自古以来，香火绵延，终日不断。辽宁青岩寺是国家批准的东北最大宗教活动场所，也是融宗教与旅游为一体的著名风景区，自然风光与人文景观交相辉映，八景十二奇观蔚为壮丽。尤以上院歪脖老母名闻天下，前来许愿者摩肩接踵，还愿者无不称奇。 青岩寺(歪脖老母) 青岩寺(歪脖老母)歪脖老母距今1500多年历史，这里香火旺盛，降香朝拜者如云。歪脖老母被信众们誉为普渡众生、有求必应的灵验之神。近年来，每日前来降香许愿，祈求人车平安，升官发财，生意兴隆，祛病强身，婚姻美满，早生贵子、金榜题名者摩肩接踵，络绎不绝。皆曰：心诚则灵。无一不验。 青岩寺(歪脖老母) 青岩寺(歪脖老母)青岩寺建筑整体分为下院、中院、上院、文殊院、圣水院等禅院。上院筑于悬崖绝壁之间，大有凌空悬挂、虚无飘渺之感。中院坐落在山腰万树丛中，讲经说法客不断，晨钟暮鼓伴蝉鸣。下院建于群峰环抱之中，有千山抱一寺，一寺镇千山之说。文殊院嵌在青岩之中，尘心世俗随风去，清云淡雾绕香烟。 青岩寺(歪脖老母) 青岩寺(歪脖老母)
                 * picList : [{"picUrl":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/hwHxnP.jpg",
                 * "picUrlSmall":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/hwHxnP_130x130_00.jpg"},
                 * {"picUrl":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/oLYCD8.jpg",
                 * "picUrlSmall":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/oLYCD8_130x130_00.jpg"},
                 * {"picUrl":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/998jSg.jpg",
                 * "picUrlSmall":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/998jSg_130x130_00.jpg"},
                 * {"picUrl":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/TCOOhH.jpg",
                 * "picUrlSmall":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/TCOOhH_130x130_00.jpg"},
                 * {"picUrl":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/JQ7R69.jpg",
                 * "picUrlSmall":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/JQ7R69_130x130_00.jpg"},
                 * {"picUrl":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/Mwp85I.jpg",
                 * "picUrlSmall":"http://pic3.40017.cn/scenery/destination/2015/04/18/01/Mwp85I_130x130_00.jpg"}]
                 * priceList : [{"type":"成人票","entityList":[{"PriceInSceneryId":9290,"PriceId":226824,
                 * "PriceName":"8.1-10.31","TicketTypeId":158647,"TicketName":"青岩寺(歪脖老母)成人票(下单后4小时生效，无需预约)",
                 * "Amount":"40","AmountAdvice":"35","BeginDate":"2015-08-01","EndDate":"2015-10-31"}]}]
                 * opentime : 8:00-15:30（15:30停止取票）
                 * coupon : 老人、儿童、军人、残疾人等持相关证件至景区自行购买优惠票。以上信息仅供参考，具体请以景区当天披露为准。
                 * attention :
                 * ①预订门票为景区通票。②门票当天有效，出园需入园，需再次购票。③为保证取票、入园顺利，预订时请务必填写真实姓名、手机号码等信息。活动提醒1.1.想实时关注最新辽宁旅游咨询&amp;
                 * 优惠景点门票，请关注辽宁旅游群：188524847（此QQ群为驴友自发旅游分享群，不代表同程网官方）
                 * ct : 2015-08-23 23:40:57.552
                 * star : 4A
                 */

                private String id;
                private String proId;
                private String proName;
                private String cityId;
                private String cityName;
                private String areaId;
                private String areaName;
                private String summary;
                private String address;
                private String name;
                private LocationBean location;
                private String price;
                private String content;
                private String opentime;
                private String coupon;
                private String attention;
                private String ct;
                private String star;
                private List<PicListBean> picList;
                private List<PriceListBean> priceList;

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

                public String getAreaId() {
                    return areaId;
                }

                public void setAreaId(String areaId) {
                    this.areaId = areaId;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
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

                public LocationBean getLocation() {
                    return location;
                }

                public void setLocation(LocationBean location) {
                    this.location = location;
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

                public String getCoupon() {
                    return coupon;
                }

                public void setCoupon(String coupon) {
                    this.coupon = coupon;
                }

                public String getAttention() {
                    return attention;
                }

                public void setAttention(String attention) {
                    this.attention = attention;
                }

                public String getCt() {
                    return ct;
                }

                public void setCt(String ct) {
                    this.ct = ct;
                }

                public String getStar() {
                    return star;
                }

                public void setStar(String star) {
                    this.star = star;
                }

                public List<PicListBean> getPicList() {
                    return picList;
                }

                public void setPicList(List<PicListBean> picList) {
                    this.picList = picList;
                }

                public List<PriceListBean> getPriceList() {
                    return priceList;
                }

                public void setPriceList(List<PriceListBean> priceList) {
                    this.priceList = priceList;
                }

                public static class LocationBean {
                    /**
                     * lat : 41.47879736
                     * lon : 121.62824592
                     */

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

                public static class PriceListBean {
                    /**
                     * type : 成人票
                     * entityList : [{"PriceInSceneryId":9290,"PriceId":226824,"PriceName":"8.1-10.31","TicketTypeId":158647,"TicketName":"青岩寺(歪脖老母)成人票(下单后4小时生效，无需预约)","Amount":"40","AmountAdvice":"35","BeginDate":"2015-08-01","EndDate":"2015-10-31"}]
                     */

                    private String type;
                    private List<EntityListBean> entityList;

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public List<EntityListBean> getEntityList() {
                        return entityList;
                    }

                    public void setEntityList(List<EntityListBean> entityList) {
                        this.entityList = entityList;
                    }

                    public static class EntityListBean {
                        /**
                         * PriceInSceneryId : 9290
                         * PriceId : 226824
                         * PriceName : 8.1-10.31
                         * TicketTypeId : 158647
                         * TicketName : 青岩寺(歪脖老母)成人票(下单后4小时生效，无需预约)
                         * Amount : 40
                         * AmountAdvice : 35
                         * BeginDate : 2015-08-01
                         * EndDate : 2015-10-31
                         */

                        private int PriceInSceneryId;
                        private int PriceId;
                        private String PriceName;
                        private int TicketTypeId;
                        private String TicketName;
                        private String Amount;
                        private String AmountAdvice;
                        private String BeginDate;
                        private String EndDate;

                        public int getPriceInSceneryId() {
                            return PriceInSceneryId;
                        }

                        public void setPriceInSceneryId(int PriceInSceneryId) {
                            this.PriceInSceneryId = PriceInSceneryId;
                        }

                        public int getPriceId() {
                            return PriceId;
                        }

                        public void setPriceId(int PriceId) {
                            this.PriceId = PriceId;
                        }

                        public String getPriceName() {
                            return PriceName;
                        }

                        public void setPriceName(String PriceName) {
                            this.PriceName = PriceName;
                        }

                        public int getTicketTypeId() {
                            return TicketTypeId;
                        }

                        public void setTicketTypeId(int TicketTypeId) {
                            this.TicketTypeId = TicketTypeId;
                        }

                        public String getTicketName() {
                            return TicketName;
                        }

                        public void setTicketName(String TicketName) {
                            this.TicketName = TicketName;
                        }

                        public String getAmount() {
                            return Amount;
                        }

                        public void setAmount(String Amount) {
                            this.Amount = Amount;
                        }

                        public String getAmountAdvice() {
                            return AmountAdvice;
                        }

                        public void setAmountAdvice(String AmountAdvice) {
                            this.AmountAdvice = AmountAdvice;
                        }

                        public String getBeginDate() {
                            return BeginDate;
                        }

                        public void setBeginDate(String BeginDate) {
                            this.BeginDate = BeginDate;
                        }

                        public String getEndDate() {
                            return EndDate;
                        }

                        public void setEndDate(String EndDate) {
                            this.EndDate = EndDate;
                        }
                    }
                }
            }
        }
    }
}
