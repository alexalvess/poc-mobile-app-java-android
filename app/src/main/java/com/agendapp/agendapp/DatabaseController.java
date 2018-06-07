package com.agendapp.agendapp;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Alex on 21/05/2016.
 */
public class DatabaseController {
    private SQLiteDatabase db;
    private Database database;

    long date = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String dateString = sdf.format(date);

    public DatabaseController(Context context){
        this.database = new Database(context);
    }

    public String insert(String data, String evento, String local){
        ContentValues values;
        long result;

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put("data", data);
        values.put("evento", evento);
        values.put("local", local);

        result = db.insert("AGENDA", null, values);
        db.close();

        if (result == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public Cursor searchAll(){
        db = database.getReadableDatabase();

        String orderBy = " _id DESC ";

        String[] campos = new String[]{"_id", "data", "evento", "local"};
        Cursor cursor = db.query("AGENDA", campos, null, null, null, null, orderBy);

        if(cursor != null) cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor searchBySpecification(){
        db = database.getReadableDatabase();

        String[] campos = new String[]{"_id", "data", "evento", "local"};
        String where = " data >= '" + this.dateString + "'";
        String orderBy = " data ASC ";

        Cursor cursor = db.query("AGENDA", campos, where, null, null, null, orderBy);

        if(cursor != null) cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public String searchLastDate(){
        HashMap<String,String> user = new HashMap<String,String>();
        db = database.getReadableDatabase();

        String sql = "SELECT data FROM AGENDA ORDER BY data DESC";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("data", cursor.getString(0));
        }
        cursor.close();
        db.close();
        return String.valueOf(user.get("data"));
    }

    public Cursor searchByDate(){
        db = database.getReadableDatabase();

        String[] campos = new String[]{"_id", "data", "evento", "local"};
        String where = " data >= '" + NotificationDay(this.dateString) + "'";

        Cursor cursor = db.query("AGENDA", campos, where, null, null, null, null);

        if(cursor != null) cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public boolean delete(int id){
        String where = "_id = " + id;
        db = database.getReadableDatabase();
        db.delete("AGENDA", where, null);
        db.close();
        return true;
    }

    public boolean update(int id, String evento, String local, String data){
        ContentValues values;
        long result;

        db = database.getWritableDatabase();

        String where = "_id = " + id;

        values = new ContentValues();
        values.put("data", data);
        values.put("evento", evento);
        values.put("local", local);

        result = db.update("AGENDA", values, where, null);
        db.close();

        if(result == -1) return false;
        else return true;
    }


    public String NotificationDay(String date){
        String dateTmp[] = date.split("-");

        int day = Integer.parseInt(dateTmp[2]);
        int month = Integer.parseInt(dateTmp[1]);
        int year = Integer.parseInt(dateTmp[0]);

        if(day < 3){
            if(day - 2 == 0) day = 1;
            if(day - 1 == 0) day = 2;
            if(month == 1){
                month = 12;
                year = year - 1;
            }
            else month = month - 1;
            if(month == 1) day = 31 - day;
            else if(month == 2){
                if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) day = 29 - day;
                else day = 28 - day;
            }
            else if(month == 3) day = 31 - day;
            else if(month == 4) day = 30 - day;
            else if(month == 5) day = 31 - day;
            else if(month == 6) day = 30 - day;
            else if(month == 7) day = 31 - day;
            else if(month == 8) day = 31 - day;
            else if(month == 9) day = 30 - day;
            else if(month == 10) day = 31 - day;
            else if(month == 11) day = 30 - day;
            else day = 31 - day;
        }
        else day = day - 3;

        return year + "-" + month + "-" + day;
    }
}
