package com.agendapp.agendapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    private Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlertDialog alert = new AlertDialog.Builder(this).create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView compromisso = (TextView) findViewById(R.id.textView2);
        TextView data = (TextView) findViewById(R.id.textView7);

        agenda = new Agenda();

        compromisso.setCursorVisible(false);
        data.setCursorVisible(false);

        if(getIntent().hasExtra("ID") && getIntent().hasExtra("EVENTO") && getIntent().hasExtra("DATA") && getIntent().hasExtra("LOCAL")){
            Bundle bundle = getIntent().getExtras();
            agenda.setId((int)bundle.getLong("ID"));
            agenda.setData(bundle.getString("DATA"));
            agenda.setEvento(bundle.getString("EVENTO"));
            agenda.setLocal(bundle.getString("LOCAL"));

            compromisso.setCursorVisible(false);
            data.setCursorVisible(false);

            compromisso.setText(agenda.getEvento());
            data.setText(agenda.getData());
        }

        final Button edit = (Button) findViewById(R.id.button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionUpdate(view, agenda.getId(), agenda.getEvento(), agenda.getData(), agenda.getLocal());
            }
        });
    }

    public void actionUpdate(View view, int id, String evento, String data, String local){
        Intent actionUpdate = new Intent(this, Main6Activity.class);
        actionUpdate.putExtra("ID", id);
        actionUpdate.putExtra("EVENTO", evento);
        actionUpdate.putExtra("DATA", data);
        actionUpdate.putExtra("LOCAL", local);
        startActivity(actionUpdate);
    }

    public void delete(View view){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        DatabaseController crud = new DatabaseController(getBaseContext());
        if(crud.delete(agenda.getId()))
            alert.setMessage("Registro deletado com sucesso!");
        else
            alert.setMessage("Erro ao deletar registro!");
        alert.show();
    }

    public void returned(View view){
        Intent returned = new Intent(this, MainActivity.class);
        startActivity(returned);

        finish();
    }
}
