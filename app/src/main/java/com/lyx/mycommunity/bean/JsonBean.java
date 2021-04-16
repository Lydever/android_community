package com.lyx.mycommunity.bean;
/**
 * @author create by liyingxia
 * 创建日期：2020/12/23 22:32
 * 包名： com.example.sqlite.OKhttp
 */

/**
 * {
 *         "resultcode":"200",
 *         "reason":"Return Successd!",
 *         "result":{
 *         "province":"广东",
 *         "city":"广州",
 *         "areacode":"020",
 *         "zip":"510000",
 *         "company":"联通",
 *         "card":""
 *         },
 *         "error_code":0
 *         }
 */

public class JsonBean {
    public String resultcode;
    public String reason;
    public Result result;

    public static class Result {
        public String province;
        public String city;
        public String areacode;
        public String zip;
        public String company;
        public String card;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }
    }


    @Override
    public String toString() {
        return "JsonBean{" +
                "reason='" + reason + '\'' +
                ", resultcode='" + resultcode + '\'' +
                ", result=" + result +
                '}';
    }
}

