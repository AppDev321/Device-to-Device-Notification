package com.example.mabrouk.sentnotifications.sendNotfication.model;

/**
 * Created by Mohamed mabrouk
 * 0201152644726
 * on 05/02/2018.  time :15:08
 */

public class Message {

    @Override
    public String toString() {
        return "Message{" +
                "to='" + to + '\'' +
                ", data=" + data +
                '}';
    }

    String to;
    NotifyData data;

    public Message(String to, NotifyData data) {
        this.to = to;
        this.data = data;
    }
}
