package com.wd.tech.home.bean;

import java.util.List;

public class SearchBean {

    /**
     * result : [{"id":61,"releaseTime":1553064018000,"source":"雨天","title":"区块链如何帮助人们更方便搞定法律服务？"},{"id":60,"releaseTime":1553063676000,"source":"侏罗纪","title":"威胁不止有51%攻击，区块链为何频遭黑客入侵？"},{"id":56,"releaseTime":1553040466000,"source":"科技行者","title":"谷歌踏入区块链搜索 触及八大最活跃网络完整数据集"},{"id":50,"releaseTime":1539582903000,"source":"全天候科技","title":"行业薪酬\u201c大跳水\u201d，区块链真的凉了？"},{"id":49,"releaseTime":1539582707000,"source":"蓝狐笔记","title":"为什么说区块链没那么简单？"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 61
         * releaseTime : 1553064018000
         * source : 雨天
         * title : 区块链如何帮助人们更方便搞定法律服务？
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
