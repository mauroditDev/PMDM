package com.example.alumno.tareasdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listadoPrincipal;
    // Definimos el adaptador que va a usar el ListView
    // Matriz con los datos del adaptador
    private ArrayList<Tarea> datos = new ArrayList<>();
    private AdaptadorTarea adaptadorTarea;

    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Context context = this.getApplicationContext();

        listadoPrincipal = (ListView) findViewById(R.id.lista);

        dBmanager = new DBmanager(context);

        datos = dBmanager.getTodos();

        adaptadorTarea = new AdaptadorTarea(this,datos);
        listadoPrincipal.setAdapter(adaptadorTarea);


    }
}
