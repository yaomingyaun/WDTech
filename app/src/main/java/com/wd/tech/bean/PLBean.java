package com.wd.tech.bean;

import java.util.List;

public class PLBean {

    /**
     * result : [{"comment":0,"content":"1111","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-05-09/20190509091210.jpg","id":797,"nickName":"gyy","praise":0,"publishTime":1557836332000,"userId":357,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":3,"communityCommentVoList":[{"content":"嘎嘎","nickName":"楚云飞","userId":389},{"content":"on","nickName":"楚云飞","userId":389},{"content":"哈哈","nickName":"楚云飞","userId":389}],"content":"sasdas","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":796,"nickName":"楚云飞","praise":12,"publishTime":1557822809000,"userId":389,"whetherFollow":2,"whetherGreat":1,"whetherVip":2},{"comment":3,"communityCommentVoList":[{"content":"12","nickName":"9939","userId":444},{"content":"12","nickName":"9939","userId":444},{"content":"1","nickName":"云闲","userId":451}],"content":"666","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":795,"nickName":"楚云飞","praise":6,"publishTime":1557822770000,"userId":389,"whetherFollow":2,"whetherGreat":1,"whetherVip":2},{"comment":0,"content":"111","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-14/4217620190514155325.png,http://mobile.bwstudent.com/images/tech/community_pic/2019-05-14/4639520190514155325.png,http://mobile.bwstudent.com/images/tech/community_pic/2019-05-14/0834220190514155325.png,http://mobile.bwstudent.com/images/tech/community_pic/2019-05-14/2390720190514155325.png","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":794,"nickName":"高","praise":4,"publishTime":1557820405000,"signature":"sss","userId":479,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":4,"communityCommentVoList":[{"content":"呃呃","nickName":"等风来_GZY","userId":507},{"content":"哈哈","nickName":"等风来_GZY","userId":507},{"content":"啦啦","nickName":"9939","userId":444}],"content":"敲的头疼","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-14/1054220190514154824.png","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-05-13/20190513171740.png","id":793,"nickName":"块签","praise":3,"publishTime":1557820104000,"signature":"好","userId":370,"whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
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
         * content : 1111
         * file :
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2019-05-09/20190509091210.jpg
         * id : 797
         * nickName : gyy
         * praise : 0
         * publishTime : 1557836332000
         * userId : 357
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
