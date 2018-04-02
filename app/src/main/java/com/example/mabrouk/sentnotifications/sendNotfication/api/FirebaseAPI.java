package com.example.mabrouk.sentnotifications.sendNotfication.api;

import com.example.mabrouk.sentnotifications.sendNotfication.model.Message;
import com.example.mabrouk.sentnotifications.sendNotfication.model.Response;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mohamed mabrouk
 * 0201152644726
 * on 05/02/2018.  time :15:06
 */

public interface FirebaseAPI {
    @Headers({"Content-Type: application/json",
            "Authorization: key=AIzaSyBDw4z8YMRVv2xT5wXrm9UYYUtdxRPRTSk"})
    @POST("/fcm/send")
    Observable<Response> sendMessage(@Body Message message);}
