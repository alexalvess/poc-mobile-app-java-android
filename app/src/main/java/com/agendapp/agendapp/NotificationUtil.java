package com.agendapp.agendapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

//import com.agendapp.agendapp.androidutils.R;

/**
 * Classe utilitária para disparar notifications
 */
public class NotificationUtil {

    private static final String TAG = "agendapp";

    public static void create(Context context, int id, Intent intent,int smallIcon, String contentTitle, String contentText, String ticker) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent para disparar o broadcast
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker(ticker)
                .setContentIntent(p)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.agenda))
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        manager.notify(id, n);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context,som);
            toque.play();
        }catch (Exception ex){}

        Log.d(TAG,"Notification criada com sucesso");
    }

    public static void createStackNotification(Context context, int id,String groupId, Intent intent,int smallIcon, String contentTitle, String contentText) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent para disparar o broadcast
        PendingIntent p = intent != null ? PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT) : null;

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(p)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setGroup(groupId)
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(id, n);

        Log.d(TAG,"Notification criada com sucesso");
    }

    // Notificação simples sem abrir intent (usada para alertas, ex: no wear)
    public static void create(Context context, int smallIcon, String contentTitle, String contentText) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(0, n);

        Log.d(TAG,"Notification criada com sucesso");
    }

    public static void cancell(Context context, int id) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public static void cancellAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
