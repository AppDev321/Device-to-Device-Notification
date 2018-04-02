package com.example.mabrouk.sentnotifications.sendNotfication.model;

/**
 * Created by Mohamed mabrouk
 * 0201152644726
 * on 05/02/2018.  time :15:07
 */

public class NotifyData {
    String title;
    String content;
    String notify_type;

    public NotifyData(String title, String content, String notify_type) {
        this.title = title;
        this.content = content;
        this.notify_type = notify_type;
    }

    @Override
    public String toString() {
        return "NotifyData{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", notify_type='" + notify_type + '\'' +
                '}';
    }
}
