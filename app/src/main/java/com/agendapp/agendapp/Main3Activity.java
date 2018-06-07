package com.agendapp.agendapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlertDialog alert = new AlertDialog.Builder(this).create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DatabaseController crud = new DatabaseController(getBaseContext());
        Cursor cursor = crud.searchBySpecification();

        String[] nomeCampos = new String[] {"data", "evento", "local"};
        int[] idViews = new int[] {R.id.data, R.id.evento, R.id.local};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.consulta,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String evento = (String) ((TextView) view.findViewById(R.id.evento)).getText();
                String data = (String) ((TextView) view.findViewById(R.id.data)).getText();
                String local = (String) ((TextView) view.findViewById(R.id.local)).getText();
                Action(view, id, evento, data, local);
            }
        });
    }

    public void Action(View view, long id, String evento, String data, String local){
        Intent action = new Intent(this, Main5Activity.class);
        action.putExtra("ID", id);
        action.putExtra("EVENTO", evento);
        action.putExtra("DATA", data);
        action.putExtra("LOCAL", local);
        startActivity(action);
    }
}