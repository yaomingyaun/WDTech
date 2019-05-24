package com.wd.tech.message.bean;

import java.util.List;

public class bean1 {
   //private String title;
    /**
     * result : [{"currentNumber":0,"customize":1,"groupId":274,"groupName":"我的好友"},{"currentNumber":0,"customize":1,"groupId":275,"groupName":"黑名单"},{"currentNumber":0,"customize":2,"groupId":290,"groupName":"第一个分组"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

   /* public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
         * currentNumber : 0
         * customize : 1
         * groupId : 274
         * groupName : 我的好友
         */
        private List<bean2.ResultBean> info;
        private int currentNumber;
        private int customize;
        private int groupId;
        private String groupName;

        public List<bean2.ResultBean> getInfo() {
            return info;
        }

        public void setInfo(List<bean2.ResultBean> info) {
            this.info = info;
        }

        public int getCurrentNumber() {
            return currentNumber;
        }

        public void setCurrentNumber(int currentNumber) {
            this.currentNumber = currentNumber;
        }

        public int getCustomize() {
            return customize;
        }

        public void setCustomize(int customize) {
            this.customize = customize;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
