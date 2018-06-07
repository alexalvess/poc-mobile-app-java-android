package com.agendapp.agendapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 21/05/2016.
 */
public class Database extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "agendapp.db";
    private static final int VERSAO = 9;

    public Database(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE AGENDA (" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " data DATE NOT NULL," +
                " evento TEXT NOT NULL," +
                " local TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS AGENDA");
        onCreate(db);
    }
}
