package com.wd.tech.message.bean;

import java.util.List;

public class bean2 {
   /* public String name; //名称
    private String qianming;//签名
    private String icon;    //头像*/
    /**
     * result : [{"friendUid":1012,"headPic":"http://172.17.8.100/images/tech/head_pic/2018-10-08/20181008085110.jpg","nickName":"周考","remarkName":"明天","vipFlag":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQianming() {
        return qianming;
    }

    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }*/

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
         * friendUid : 1012
         * headPic : http://172.17.8.100/images/tech/head_pic/2018-10-08/20181008085110.jpg
         * nickName : 周考
         * remarkName : 明天
         * vipFlag : 2
         */

        private int friendUid;
        private String headPic;
        private String nickName;
        private String remarkName;
        private int vipFlag;

        public int getFriendUid() {
            return friendUid;
        }

        public void setFriendUid(int friendUid) {
            this.friendUid = friendUid;
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

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public int getVipFlag() {
            return vipFlag;
        }

        public void setVipFlag(int vipFlag) {
            this.vipFlag = vipFlag;
        }
    }
}
