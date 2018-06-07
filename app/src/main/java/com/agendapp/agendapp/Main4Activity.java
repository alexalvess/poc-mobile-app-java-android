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

public class Main4Activity extends AppCompatActivity {

    private ListView lista1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlertDialog alert = new AlertDialog.Builder(this).create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        DatabaseController crud = new DatabaseController(getBaseContext());
        Cursor cursor = crud.searchAll();

        String[] nomeCampos = new String[] {"data", "evento", "local"};
        int[] idViews1 = new int[] {R.id.data1, R.id.evento1, R.id.local1};

        SimpleCursorAdapter adaptador1 = new SimpleCursorAdapter(getBaseContext(),
                R.layout.consulta1,cursor,nomeCampos,idViews1, 0);
        lista1 = (ListView)findViewById(R.id.listView1);
        lista1.setAdapter(adaptador1);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String evento = (String) ((TextView) view.findViewById(R.id.evento1)).getText();
                String data = (String) ((TextView) view.findViewById(R.id.data1)).getText();
                String local = (String) ((TextView) view.findViewById(R.id.local1)).getText();
                Action(view, id, evento, data, local);
            }
        });
    }

    public void Action(View view, long id, String evento, String data,  String local){
        Intent action = new Intent(this, Main5Activity.class);
        action.putExtra("ID", id);
        action.putExtra("EVENTO", evento);
        action.putExtra("DATA", data);
        action.putExtra("LOCAL", local);
        startActivity(action);
    }
}
