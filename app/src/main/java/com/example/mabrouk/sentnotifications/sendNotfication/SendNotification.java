package com.example.mabrouk.sentnotifications.sendNotfication;

import android.content.Context;

import com.example.mabrouk.sentnotifications.sendNotfication.model.Message;
import com.example.mabrouk.sentnotifications.sendNotfication.model.Response;
import com.example.mabrouk.sentnotifications.sendNotfication.api.FirebaseAPI;
import com.example.mabrouk.sentnotifications.sendNotfication.api.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mohamed mabrouk
 * 0201152644726
 * on 05/02/2018.  time :15:03
 */

public class SendNotification {
    private   Context context;
    private static SendNotification notification;

    public static SendNotification getNotification(Context context){
        if (notification==null){
            synchronized (SendNotification.class){
                notification=new SendNotification(context);
            }
        }
        return notification;
    }

    private SendNotification(Context context){
        this.context=context;
    }

    public void sendNotification(Message message, Observer<Response> observer){
        FirebaseAPI api= RetrofitClient.getmRetrofit().create(FirebaseAPI.class);
        api.sendMessage(message).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeWith(observer);
     }

}
