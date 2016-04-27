package com.example.adam.timemanagerultimate.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.adam.timemanagerultimate.service.UpdaterService;

public class BootReciever extends BroadcastReceiver {
    public BootReciever() {
    }

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive()");

        Intent actIntent = new Intent(context, UpdaterService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, actIntent, 0);

//        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        am.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                System.currentTimeMillis(),
//                5000, // 1000 * 60 * 15,s
//                pi);

    }
}
