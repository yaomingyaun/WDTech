package com.wd.tech.message.bean;

import java.io.Serializable;

public class FindUserBeans implements Serializable {

    /**
     * result : {"email":"999@163.com","headPic":"http://172.17.8.100/images/tech/head_pic/2018-09-30/20180930093918.jpg","integral":0,"nickName":"江山","phone":"16619998889","sex":1,"signature":"打江山","userId":1078,"whetherVip":2,"whetherFaceId":1}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean implements Serializable{
        /**
         * email : 999@163.com
         * headPic : http://172.17.8.100/images/tech/head_pic/2018-09-30/20180930093918.jpg
         * integral : 0
         * nickName : 江山
         * phone : 16619998889
         * sex : 1
         * signature : 打江山
         * userId : 1078
         * whetherVip : 2
         * whetherFaceId : 1
         */

        private String email;
        private String headPic;
        private String integral;
        private String nickName;
        private String phone;
        private String sex;
        private String signature;
        private String userId;
        private String whetherVip;
        private String whetherFaceId;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(String whetherVip) {
            this.whetherVip = whetherVip;
        }

        public String getWhetherFaceId() {
            return whetherFaceId;
        }

        public void setWhetherFaceId(String whetherFaceId) {
            this.whetherFaceId = whetherFaceId;
        }
    }
}
