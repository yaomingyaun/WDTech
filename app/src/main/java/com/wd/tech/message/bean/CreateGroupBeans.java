package com.wd.tech.message.bean;

public class CreateGroupBeans {

    /**
     * message : 创建成功
     * status : 0000
     * groupId : 12
     */

    private String message;
    private String status;
    private int groupId;

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
