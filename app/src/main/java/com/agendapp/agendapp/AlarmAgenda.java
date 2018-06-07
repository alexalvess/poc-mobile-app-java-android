package com.agendapp.agendapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by Alex on 05/06/2016.
 */
public class AlarmAgenda extends BroadcastReceiver{
    private static final String TAG = "agendapp";
    public static final String ACTION = "com.agendapp.agendapp.AGENDA";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Você tem um compromisso: " + new Date());

        Intent notifIntent = new Intent(context,Main3Activity.class);

        NotificationUtil.create(context, 1, notifIntent, R.drawable.agenda, "Compromisso", "Preparesse, você tem compromissos próximos", "AgendApp - Novos Compromissos");
    }
}
