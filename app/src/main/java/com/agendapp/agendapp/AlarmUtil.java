package com.agendapp.agendapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Alex on 05/06/2016.
 */
public class AlarmUtil {
    private static final String TAG = "agendapp";

    // Agenda o alarme com repeat
    public static void scheduleRepeat(Context context, Intent intent, long triggerAtMillis, long intervalMillis) {
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarme.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, p);
        Log.d("LOG", "Alarme agendado com sucesso com repeat");
    }

    public static void cancel(Context context, Intent intent) {
        AlarmManager alarme = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarme.cancel(p);
        Log.d("LOG", "Alarme cancelado com sucesso.");
    }
}
