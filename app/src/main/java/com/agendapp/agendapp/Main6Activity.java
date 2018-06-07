package com.agendapp.agendapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Main6Activity extends AppCompatActivity {
    private Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AlertDialog alert = new AlertDialog.Builder(this).create();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        agenda = new Agenda();

        if(getIntent().hasExtra("ID") && getIntent().hasExtra("EVENTO") && getIntent().hasExtra("DATA") && getIntent().hasExtra("LOCAL")) {
            Bundle bundle = getIntent().getExtras();
            agenda.setId(bundle.getInt("ID"));
            agenda.setEvento(bundle.getString("EVENTO"));
            agenda.setData(bundle.getString("DATA"));
            agenda.setLocal(bundle.getString("LOCAL"));

            EditText evento = (EditText)findViewById(R.id.textView6);
            EditText local = (EditText)findViewById(R.id.editText4);
            DatePicker data = (DatePicker)findViewById(R.id.dpResult);

            evento.setText(agenda.getData());

            String array[] = agenda.getData().split("-");
            int day = Integer.parseInt(array[2]);
            int month = Integer.parseInt(array[1]);
            int year = Integer.parseInt(array[0]);

            evento.setText(agenda.getEvento());
            local.setText(agenda.getLocal());
            data.updateDate(year, month, day);

            Button update = (Button) findViewById(R.id.button4);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edit(view, agenda.getId());
                }
            });
        }
    }

    public void edit(View view, int id){
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

        if(crud.update(agenda.getId(), agenda.getEvento(), agenda.getLocal(), agenda.getData())) alert.setMessage("Edição concluída!");
        else alert.setMessage("Erro ao editar!");
        alert.show();

        Intent action = new Intent(this, MainActivity.class);
        startActivity(action);

        finish();
    }

    public void returned(View view){
        Intent returned = new Intent(this, Main5Activity.class);
        startActivity(returned);

        finish();
    }
}
