package com.example.alumno.peces;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Pez> datos;
    AdaptadorPez adaptadoPeces;
    ListView lista;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        datos = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.itemizado,R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        textView = (TextView) findViewById(R.id.titulo);

        lista = (ListView) findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                imagen(position);

            }
        });

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id){
                cargar(position);
                //adaptadoPeces.notifyDataSetChanged();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                //adaptadoPeces.notifyDataSetChanged();
            }
        });


    }

    public void cargar(int position){
        datos = new ArrayList<>();
        String linea;
        String[] cont;
        InputStream fraw;
        BufferedReader brin;
        if(position == 0) {
            fraw =getResources().openRawResource(R.raw.peces);
            brin = new BufferedReader(new InputStreamReader(fraw, UTF_8));
        }
        else{
            fraw =getResources().openRawResource(R.raw.algaseinvertebrados);
            brin = new BufferedReader(new InputStreamReader(fraw, ISO_8859_1));
        }

        try{

            linea = brin.readLine();
            while(linea!=null) {
                cont = linea.split(",");
                Pez nuevo = new Pez();
                switch(position){
                    case 0:
                        nuevo.img = cont[0];
                        nuevo.nombreCom = cont[1];
                        nuevo.nombreCien = cont[2];
                        nuevo.tamano = cont[3];
                        nuevo.habitat = cont [4];
                        break;

                    case 1:
                        nuevo.img = cont[0];
                        nuevo.nombreCom = cont[1];
                        nuevo.nombreCien = cont[3];
                        nuevo.tamano = cont[4];
                        nuevo.habitat = cont [5];
                        textView.setText(getString(R.string.invertebrados));
                        break;
                }
                datos.add(nuevo);
                linea = brin.readLine();
            };
            brin.close();
            fraw.close();

            lista = (ListView) findViewById(R.id.lista);
            adaptadoPeces = new AdaptadorPez(this,datos);
            lista.setAdapter(adaptadoPeces);
        }
        catch (Exception e){
            Log.v("error","he explotao...");
        }

    }

    public void imagen(int position){

        Intent i = new Intent(this, Imagen.class);
        i.putExtra("imagen", datos.get(position).img);
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);

    }

    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
