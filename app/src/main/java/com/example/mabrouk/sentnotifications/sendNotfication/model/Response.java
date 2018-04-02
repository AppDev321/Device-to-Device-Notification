package com.example.mabrouk.sentnotifications.sendNotfication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammad mabrouk
 * 0201152644726
 * on 4/2/2018.  time :23:29
 */
public class Response {
    @SerializedName("success")
    private int success;
    @SerializedName("failure")
    private int failure;


    public boolean IsSuccess() {
        return success==1;
    }

    public boolean IsFailure() {
        return failure==1;
    }
}
