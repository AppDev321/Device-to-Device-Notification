package com.example.mabrouk.sentnotifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mohamed mabrouk
 * 0201152644726
 * on 07/01/2018.  time :17:08
 */

public class FireBaseIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token", token + "");
    }
}
