package com.wd.tech.bean;

import java.util.List;

public class FindCollectBean {
    /**
     * result : [{"createTime":1538104445000,"id":3,"infoId":1,"thumbnail":"https://img.huxiucdn.com/article/cover/201808/28/103850448205.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"关于滴滴顺风车事件的几点思考"}]
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
         * createTime : 1538104445000
         * id : 3
         * infoId : 1
         * thumbnail : https://img.huxiucdn.com/article/cover/201808/28/103850448205.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg
         * title : 关于滴滴顺风车事件的几点思考
         */

        private long createTime;
        private int id;
        private int infoId;
        private String thumbnail;
        private String title;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
