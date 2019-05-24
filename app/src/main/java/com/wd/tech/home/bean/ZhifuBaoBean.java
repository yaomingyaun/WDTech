package com.wd.tech.home.bean;

import java.io.Serializable;

public class ZhifuBaoBean implements Serializable {
    private String message;
    private String status;
    private String result;

    public ZhifuBaoBean(String message, String status, String result) {
        this.message = message;
        this.status = status;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
