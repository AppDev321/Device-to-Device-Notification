package com.example.mabrouk.sentnotifications;

import android.app.Application;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by Mohammad mabrouk
 * 0201152644726
 * on 4/2/2018.  time :23:54
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("token", FirebaseInstanceId.getInstance().getToken() + "");
        FirebaseMessaging.getInstance().subscribeToTopic("mabrouk");
    }
}
