package com.agendapp.agendapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main2Activity extends AppCompatActivity {
    private Agenda agenda;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        agenda = new Agenda();
    }

    public void insertEvent(View view){
        int flag = 0;
        AlertDialog alert = new AlertDialog.Builder(this).create();
        DatabaseController crud = new DatabaseController(this);

        EditText etEvento = (EditText)findViewById(R.id.textView6);
        DatePicker dtData = (DatePicker)findViewById(R.id.dpResult);
        EditText etLocal = (EditText)findViewById(R.id.editText4);

        agenda.setEvento(etEvento.getText().toString());
        int day = dtData.getDayOfMonth();
        int month = dtData.getMonth() + 1;
        int year = dtData.getYear();
        String dayTmp, monthTmp;

        if(day < 10) dayTmp = "0" + day;
        else dayTmp = "" + day;
        if(month < 10) monthTmp = "0" + month;
        else monthTmp = "" + month;
        agenda.setData(year + "-" + monthTmp + "-" + dayTmp);
        agenda.setLocal(etLocal.getText().toString());

        if(flag == 0) {
            if (agenda.getEvento().equals("") || agenda.getLocal().equals("")) {
                alert.setMessage("Alguns campos não estão preenchidos");
                alert.show();
            } else {
                result = crud.insert(agenda.getData(), agenda.getEvento(), agenda.getLocal());
                Toast.makeText(this.getApplicationContext(), result, Toast.LENGTH_LONG).show();

                Intent action = new Intent(this, MainActivity.class);
                startActivity(action);

                finish();
            }
        }
    }
}
