package com.tcsoft.searchmachinary.bean;

/**
 * Created by Admin on 2019/5/17.
 */

public class Consult {

    private String text;
    private boolean isClient;
    private String time;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
