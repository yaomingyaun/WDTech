package com.wd.tech.bean;

import java.util.List;

public class NewsPLAllBean {

    /**
     * result : [{"commentTime":1555732301000,"content":"111111","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-20/20190420153701.jpg","nickName":"王钱","userId":134},{"commentTime":1555731807000,"content":"111111111","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-20/20190420153701.jpg","nickName":"王钱","userId":134},{"commentTime":1555731804000,"content":"111111","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-20/20190420153701.jpg","nickName":"王钱","userId":134},{"commentTime":1555731629000,"content":"11111","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-20/20190420153701.jpg","nickName":"王钱","userId":134},{"commentTime":1555731629000,"content":"11111","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-20/20190420153701.jpg","nickName":"王钱","userId":134}]
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
         * commentTime : 1555732301000
         * content : 111111
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2019-04-20/20190420153701.jpg
         * nickName : 王钱
         * userId : 134
         */

        private long commentTime;
        private String content;
        private String headPic;
        private String nickName;
        private String userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
