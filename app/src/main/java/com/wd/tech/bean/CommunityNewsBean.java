package com.wd.tech.bean;


import java.util.List;

public class CommunityNewsBean {

    /**
     * result : [{"comment":0,"content":"首发","file":"http://172.17.8.100/images/tech/head_pic/2018-09-20/20180920081958.jpg","id":18,"nickName":"小白","power":2,"praise":0,"publishTime":1538040675000,"userId":1012,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"content":"天气清凉","file":"D:/image/2018-09-22/3497620180922090755.jpg,D:/image/2018-09-22/8493520180922090755.jpg,D:/image/2018-09-22/1459620180922090755.jpg","headPic":"D:/image/2018-09-19/20180919083221.jpg","id":15,"nickName":"小明","power":1,"praise":0,"publishTime":1537578430000,"signature":"秋天不回来","userId":1010,"whetherFollow":2,"whetherGreat":1,"whetherVip":2}]
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
         * comment : 0
         * content : 首发
         * file : http://172.17.8.100/images/tech/head_pic/2018-09-20/20180920081958.jpg
         * id : 18
         * nickName : 小白
         * power : 2
         * praise : 0
         * publishTime : 1538040675000
         * userId : 1012
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         * communityCommentVoList : [{"content":"嘎嘎","nickName":"楚云飞","userId":389},{"content":"on","nickName":"楚云飞","userId":389},{"content":"哈哈","nickName":"楚云飞","userId":389}]
         * signature : sss
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private int userId;
        private int whetherFollow;
        private int whetherGreat;
        private int whetherVip;
        private String signature;
        private List<CommunityCommentVoListBean> communityCommentVoList;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public List<CommunityCommentVoListBean> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<CommunityCommentVoListBean> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }

        public static class CommunityCommentVoListBean {
            /**
             * content : 嘎嘎
             * nickName : 楚云飞
             * userId : 389
             */

            private String content;
            private String nickName;
            private int userId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }

}
