package com.jues.httputil.entity;

import java.util.List;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/18
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class AdEntity {

    /**
     * result_data : {"city_list":[{"city_id":"2","city_name":"北京市"},{"city_id":"3","city_name":"天津市"}],"img":"http://images.fa.net/uploads/images/02/05/2017/a838e7a5b71ed429debdd6ab3efcf370.png","img_url":"http://www.baidu.com","kefu":"1576787778"}
     * result_code : 0000
     * result_info :
     */

    private ResultDataBean result_data;
    private String result_code;
    private String result_info;

    public ResultDataBean getResult_data() {
        return result_data;
    }

    public void setResult_data(ResultDataBean result_data) {
        this.result_data = result_data;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_info() {
        return result_info;
    }

    public void setResult_info(String result_info) {
        this.result_info = result_info;
    }

    public static class ResultDataBean {
        /**
         * city_list : [{"city_id":"2","city_name":"北京市"},{"city_id":"3","city_name":"天津市"}]
         * img : http://images.fa.net/uploads/images/02/05/2017/a838e7a5b71ed429debdd6ab3efcf370.png
         * img_url : http://www.baidu.com
         * kefu : 1576787778
         */

        private String img;
        private String img_url;
        private String kefu;
        private List<CityListBean> city_list;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getKefu() {
            return kefu;
        }

        public void setKefu(String kefu) {
            this.kefu = kefu;
        }

        public List<CityListBean> getCity_list() {
            return city_list;
        }

        public void setCity_list(List<CityListBean> city_list) {
            this.city_list = city_list;
        }

        public static class CityListBean {
            /**
             * city_id : 2
             * city_name : 北京市
             */

            private String city_id;
            private String city_name;

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }
        }
    }
}
