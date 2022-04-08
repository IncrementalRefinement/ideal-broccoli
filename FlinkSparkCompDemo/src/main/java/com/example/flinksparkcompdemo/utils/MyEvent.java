package com.example.flinksparkcompdemo.utils;

import java.time.ZonedDateTime;

public class MyEvent {

    private String timestamp;
    private String msg;

    public MyEvent(String timestamp, String msg) {
        this.timestamp = timestamp;
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "timestamp='" + timestamp + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
