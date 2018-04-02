package com.example.mabrouk.sentnotifications;

import android.os.Build;
import android.util.Log;

import com.example.mabrouk.sentnotifications.sendNotfication.Notifications;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by mohamed mabrouk
 * 0201152644726
 * on 07/01/2018.  time :17:07
 */

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        Log.d("messageeeeeeeeeee", data.toString());
        showNotification(data.get("title"),data.get("content"));
    }

    private void showNotification(String title, String body) {
        Log.d("message", title+ " : "+body);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notifications.With(this).NotificationAndroidOBuilder(title, body, R.mipmap.ic_launcher_round)
                    .CreateChannel().CommitChannel().setAutoCancel(true).notifys();
        } else {
            Notifications.With(this).NotificationNormalBuilder(title, body, R.mipmap.ic_launcher_round).setAutoCancel(true).notifys();
        }
    }
}
