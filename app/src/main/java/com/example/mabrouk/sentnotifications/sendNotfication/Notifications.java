package com.example.mabrouk.sentnotifications.sendNotfication;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Mohamed mabrouk
 * 0201152644726
 * on 23/01/2018.  time :10:24
 */

public class Notifications extends ContextWrapper {
    private static Notifications notifications;
    private Context mContext;
    private NotificationManager mManager;

    public static Notifications With(Context mContext) {
        if (notifications == null) {
            synchronized (Notifications.class) {
                notifications = NotificationBuilder.build(mContext);
            }
        }

        return notifications;
    }

    public enum Importance {
        DEFAULT, HIGH, LOW, MAX, MIN, NONE, UNSPECIFIED
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getImportance(Importance importance) {
        switch (importance) {
            case DEFAULT:
                return NotificationManager.IMPORTANCE_DEFAULT;
            case HIGH:
                return NotificationManager.IMPORTANCE_HIGH;
            case LOW:
                return NotificationManager.IMPORTANCE_LOW;
            case MAX:
                return NotificationManager.IMPORTANCE_MAX;
            case MIN:
                return NotificationManager.IMPORTANCE_MIN;
            case NONE:
                return NotificationManager.IMPORTANCE_NONE;
            case UNSPECIFIED:
                return NotificationManager.IMPORTANCE_UNSPECIFIED;
            default:
                return NotificationManager.IMPORTANCE_HIGH;
        }
    }

    private Notifications(Context context) {
        super(context);
        this.mContext = context;
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationAndroidO NotificationAndroidOBuilder(String title, String body, int smallIcon) {
        return new NotificationAndroidO(mContext, title, body, smallIcon);
    }

    public NotificationNormal NotificationNormalBuilder(String title, String body, int smallIcon) {
        return new NotificationNormal(mContext, title, body, smallIcon);
    }


    public static class NotificationBuilder {
        public static Notifications build(Context context) {
            return new Notifications(context);
        }
    }


    public class NotificationAndroidO extends NotificationNormal {
        private NotificationChannel notificationChannel = null;
        private String channelId;

        public NotificationAndroidO(Context context, String title, String body, int smallIcon) {
            super(context, title, body, smallIcon);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO CreateChannel(String channelId, String channelName, Importance importance) {
            notificationChannel = new NotificationChannel(channelId, channelName, getImportance(importance));
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            this.channelId = channelId;

            return this;
        }

        public NotificationChannel getNotificationChannel() {
            return notificationChannel;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO CreateChannel() {
            this.channelId = getChannaleId();
            notificationChannel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO enableLights(boolean b) {
            notificationChannel.enableLights(b);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO enableVibration(boolean b) {
            notificationChannel.enableVibration(b);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private String getChannaleId() {
            List<String> list = new ArrayList<>();
            String s = getPackageName();

            for (char c : s.toCharArray()) {
                list.add(String.valueOf(c));
            }
            Collections.shuffle(list);

            s = "";
            for (String s1 : list) {
                s += s1;
            }
            Log.d("id", s + "");
            return s;
        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO setLightColor(int color) {
            notificationChannel.setLightColor(Color.GREEN);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO setLockscreenVisibility(int lockscreenVisibility) {
            notificationChannel.setLockscreenVisibility(lockscreenVisibility);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NotificationAndroidO CommitChannel() {
            getManager().createNotificationChannel(notificationChannel);
            builder.setChannelId(channelId);
            return this;
        }


    }

    public class NotificationNormal {
        public Notification.Builder builder = null;

        public NotificationNormal(Context context, String title, String body, int smallIacon) {
            builder = new Notification.Builder(context)
                    .setContentText(body)
                    .setContentTitle(title).setSmallIcon(smallIacon)
                    .setAutoCancel(true);
        }

        public NotificationNormal setAutoCancel(boolean b) {
            builder.setAutoCancel(true);
            return this;
        }

        public NotificationNormal setSmallIcon(int icon) {
            builder.setSmallIcon(icon);
            return this;
        }

        public NotificationNormal setCustomBigContentView(RemoteViews remoteViews) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                builder.setCustomBigContentView(remoteViews);
            }
            return this;
        }

        public NotificationNormal setContentIntent(Intent intent) {
            PendingIntent pIntent = PendingIntent.getActivity(mContext, (int) System.currentTimeMillis(), intent, 0);
            builder.setContentIntent(pIntent);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
        public NotificationNormal addActions(Notification.Action... actions) {
            for (int i = 0; i < actions.length; i++) {
                builder.addAction(actions[i]);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StatusBarNotification[] statusBarNotifications=getManager().getActiveNotifications();
                for (StatusBarNotification barNotification:statusBarNotifications) {
                    Log.d("barNotification", barNotification.getId() + "");
                }
            }

            return this;
        }

        public void notifys() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StatusBarNotification[] statusBarNotifications=getManager().getActiveNotifications();
                for (StatusBarNotification barNotification:statusBarNotifications) {
                    Log.d("barNotification", barNotification.getId() + "");
                }
            }
            int id=getId();
            Log.d("barNotification", id + "id");
            getManager().notify(id, builder.build());
        }

        public void notify(int id) {
            getManager().notify(id, builder.build());
        }

        public void cancel(int id) {
            getManager().cancel(id);
        }

        public void cancelAll(){
            getManager().cancelAll();
        }

        public int getId() {
            Random rand = new Random();
            return rand.nextInt(999999) + 1;
            // return  (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        }
    }
}
