package com.incorcadit16.photogallery;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Received intent " + intent.getAction());
        if (getResultCode() != Activity.RESULT_OK) {
            // Активность переднего плана сейчас на экране
            return;
        }

        int requestCode = intent.getIntExtra(PollService.REQUEST_CODE,0);
        Notification notification = intent.getParcelableExtra(PollService.NOTIFICATION);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(requestCode,notification);
    }
}
