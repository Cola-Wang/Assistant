package com.miki.assistant.model;

import java.util.List;

/**
 * 包名:      com.miki.assistant.model
 * 文件名:     LifeStyleModel.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 10:34
 * 描述:      LifeStyleModel
 */

public class LifeStyleModel {


    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101270101","location":"成都","parent_city":"成都","admin_area":"四川","cnty":"中国","lat":"30.65946198","lon":"104.06573486","tz":"+8.00"}
         * update : {"loc":"2018-08-07 09:48","utc":"2018-08-07 01:48"}
         * status : ok
         * lifestyle : [{"type":"comf","brf":"较不舒适","txt":"白天虽然有雨，但仍无法削弱较高气温带来的暑意，同时降雨造成湿度加大会您感到有些闷热，不很舒适。"},{"type":"drsg","brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},{"type":"flu","brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},{"type":"sport","brf":"较不宜","txt":"有降水，推荐您在室内进行低强度运动；若坚持户外运动，须注意选择避雨防滑地点，并携带雨具。"},{"type":"trav","brf":"适宜","txt":"有降水，稍热，但有微风伴您一路同行，适宜旅游，可不要错过机会呦！请注意携带雨具或避开下雨时段。"},{"type":"uv","brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},{"type":"cw","brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"type":"air","brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private List<LifestyleBean> lifestyle;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
        }

        public static class BasicBean {
            /**
             * cid : CN101270101
             * location : 成都
             * parent_city : 成都
             * admin_area : 四川
             * cnty : 中国
             * lat : 30.65946198
             * lon : 104.06573486
             * tz : +8.00
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

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

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2018-08-07 09:48
             * utc : 2018-08-07 01:48
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class LifestyleBean {
            /**
             * type : comf
             * brf : 较不舒适
             * txt : 白天虽然有雨，但仍无法削弱较高气温带来的暑意，同时降雨造成湿度加大会您感到有些闷热，不很舒适。
             */

            private String type;
            private String brf;
            private String txt;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }
}
