package com.wd.tech.bean;

import java.util.List;

public class CommentListBean {


    /**
     * result : ["<comment userId='393' nickName='秋天的落\n\n\n' content='54444444444'><\/comment>","<comment userId='393' nickName='秋天的落\n\n\n' content='111'><\/comment>","<comment userId='134' nickName='王钱' content='111111'><\/comment>","<comment userId='134' nickName='王钱' content='111111111'><\/comment>","<comment userId='134' nickName='王钱' content='111111'><\/comment>"]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<String> result;

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
