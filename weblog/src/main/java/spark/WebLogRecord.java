package spark;

import java.io.Serializable;

public class WebLogRecord implements Serializable {
    String id;
    String date;
    String hour;
    String minute;
    String url;

    public WebLogRecord(String id, String date, String hour, String minute, String url) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
