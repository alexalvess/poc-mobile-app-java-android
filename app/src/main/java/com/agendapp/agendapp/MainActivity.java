package com.agendapp.agendapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private PendingIntent p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Notification();
    }

    public int getDiferenceTime(){
        DatabaseController crud = new DatabaseController(getBaseContext());
        if(crud.searchLastDate() != null) {
            String dataT = crud.searchLastDate();

            long date = System.currentTimeMillis();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            String dateString = df.format(date);

            try {
                Date d1 = df.parse(dateString);
                Date d2 = df.parse(dataT);
                long dt = (d2.getTime() - d1.getTime()) + 3600000;
                long dias = dt / 86400000;
                return  (int) dias;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public long getTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 5);
        long time = c.getTimeInMillis();
        return time;
    }

    public void Notification() {
        Log.d("LOG", getDiferenceTime() + "");
        if(getDiferenceTime() <= 3){
            Intent intent = new Intent(AlarmAgenda.ACTION);
            AlarmUtil.scheduleRepeat(this, intent, getTime(), (60 * 1000) * 60 * 24);
            Log.d("LOG ", "Ativando Alarme");
        }
        else CancelNotification();
    }

    public void CancelNotification() {
        Intent intent = new Intent(AlarmAgenda.ACTION);
        AlarmUtil.cancel(this,intent);
        Toast.makeText(this,"Alarme cancelado",Toast.LENGTH_SHORT).show();
    }

    public void newEvent(View view){
        Intent main = new Intent(this, Main2Activity.class);
        startActivity(main);
    }

    public void showEventByDate(View view){
        Intent main = new Intent(this, Main3Activity.class);
        startActivity(main);
    }

    public void showAllEvent(View view){
        Intent main = new Intent(this, Main4Activity.class);
        startActivity(main);
    }
}
