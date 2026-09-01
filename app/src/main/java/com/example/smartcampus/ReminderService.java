package com.example.smartcampus;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ReminderService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Handler().postDelayed(() -> {

            NotificationManager nm =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Notification notif = new NotificationCompat.Builder(this, "channel")
                    .setContentTitle("Smart Campus")
                    .setContentText("You have a task reminder!")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .build();

            nm.notify(1, notif);

        }, 5000);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}