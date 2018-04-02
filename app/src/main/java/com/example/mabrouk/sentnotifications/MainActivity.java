package com.example.mabrouk.sentnotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mabrouk.sentnotifications.sendNotfication.SendNotification;
import com.example.mabrouk.sentnotifications.sendNotfication.model.Message;
import com.example.mabrouk.sentnotifications.sendNotfication.model.NotifyData;
import com.example.mabrouk.sentnotifications.sendNotfication.model.Response;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendToMe(String token) {
        NotifyData notifyData = new NotifyData("hello", token, "test");
        Message message = new Message(token, notifyData);
        Log.d("Message", message.toString() + "");

        SendNotification.getNotification(this).sendNotification(message, new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response message) {
                 Log.d("Messagesssssss", message.IsSuccess() + "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Message", e.getMessage() + "");

            }

            @Override
            public void onComplete() {
                Log.d("Message", "onComplete");

            }
        });
    }



    public void send(View view) {
        sendToMe("/topics/mabrouk");
        //sendToMe("dXR4vep1oKE:APA91bFX245bOQYc6haq0hhipgbgij-Lstis2087JvfkojpOXD9c0jPNaOmjmBoJOyg5T4SbDtuHECur_3KbnrMmPwhb_AVfwIk0ZVZtAdtSGALwsC8_bSh9QAIh9zDVbmn7TBf5xvgX");
    }
}
