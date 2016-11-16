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
import java.util.ArrayList;

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

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.itemizado,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        String a = spinner.getSelectedItem().toString();
        textView = (TextView) findViewById(R.id.titulo);
/*
        if(a.equals(getResources().getString(R.string.spinner1))){
            cargar(0);
        }
        else{
            cargar(1);
        }

*/

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
/*
    public  void cargar(String string){
        if(string.equals(getResources().getString(R.string.spinner1)))
            cargar(0);
        else {
            cargar(1);
            System.out.println("cargando el 1");
        }
    }
*/
    public void cargar(int position){
        datos = new ArrayList<>();
        String linea;
        String[] cont;
        InputStream fraw;
        if(position == 0) {
            fraw =getResources().openRawResource(R.raw.peces);
        }
        else{
            fraw =getResources().openRawResource(R.raw.algaseinvertebrados);
        }
        BufferedReader brin =
                new BufferedReader(new InputStreamReader(fraw));
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
