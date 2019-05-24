package com.wd.tech;

import com.wd.tech.message.bean.bean2;

import java.util.List;

public class bean1 {
    private String title;
    private List<bean2> info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<bean2> getInfo() {
        return info;
    }

    public void setInfo(List<bean2> info) {
        this.info = info;
    }
}
