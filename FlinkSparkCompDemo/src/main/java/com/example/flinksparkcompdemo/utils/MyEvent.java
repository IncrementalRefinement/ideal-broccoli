package com.example.flinksparkcompdemo.utils;

import java.time.ZonedDateTime;

public class MyEvent {

    private ZonedDateTime timestamp;
    private String msg;

    public MyEvent(ZonedDateTime timestamp, String msg) {
        this.timestamp = timestamp;
        this.msg = msg;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
