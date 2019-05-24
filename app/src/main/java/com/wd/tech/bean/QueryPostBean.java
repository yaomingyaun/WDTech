package com.wd.tech.bean;

import java.util.List;

public class QueryPostBean {

    /**
     * result : [{"communityUserPostVoList":[{"comment":2,"content":"欧欧欧莱雅","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-15/5796020190515190334.png","id":810,"praise":1,"whetherGreat":2}],"communityUserVo":{"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"小太阳","power":2,"signature":"啦啦啦啦","userId":479,"whetherFollow":2,"whetherMyFriend":2}}]
     * message : 查詢成功
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
         * communityUserPostVoList : [{"comment":2,"content":"欧欧欧莱雅","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-05-15/5796020190515190334.png","id":810,"praise":1,"whetherGreat":2}]
         * communityUserVo : {"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"小太阳","power":2,"signature":"啦啦啦啦","userId":479,"whetherFollow":2,"whetherMyFriend":2}
         */

        private CommunityUserVoBean communityUserVo;
        private List<CommunityUserPostVoListBean> communityUserPostVoList;

        public CommunityUserVoBean getCommunityUserVo() {
            return communityUserVo;
        }

        public void setCommunityUserVo(CommunityUserVoBean communityUserVo) {
            this.communityUserVo = communityUserVo;
        }

        public List<CommunityUserPostVoListBean> getCommunityUserPostVoList() {
            return communityUserPostVoList;
        }

        public void setCommunityUserPostVoList(List<CommunityUserPostVoListBean> communityUserPostVoList) {
            this.communityUserPostVoList = communityUserPostVoList;
        }

        public static class CommunityUserVoBean {
            /**
             * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
             * nickName : 小太阳
             * power : 2
             * signature : 啦啦啦啦
             * userId : 479
             * whetherFollow : 2
             * whetherMyFriend : 2
             */

            private String headPic;
            private String nickName;
            private int power;
            private String signature;
            private int userId;
            private int whetherFollow;
            private int whetherMyFriend;

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getPower() {
                return power;
            }

            public void setPower(int power) {
                this.power = power;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
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

            public int getWhetherMyFriend() {
                return whetherMyFriend;
            }

            public void setWhetherMyFriend(int whetherMyFriend) {
                this.whetherMyFriend = whetherMyFriend;
            }
        }

        public static class CommunityUserPostVoListBean {
            /**
             * comment : 2
             * content : 欧欧欧莱雅
             * file : http://mobile.bwstudent.com/images/tech/community_pic/2019-05-15/5796020190515190334.png
             * id : 810
             * praise : 1
             * whetherGreat : 2
             */

            private int comment;
            private String content;
            private String file;
            private int id;
            private int praise;
            private int whetherGreat;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public int getWhetherGreat() {
                return whetherGreat;
            }

            public void setWhetherGreat(int whetherGreat) {
                this.whetherGreat = whetherGreat;
            }
        }
    }
}
