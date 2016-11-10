package com.example.alumno.peces;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Pez> datos = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.itemizado,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        String a = spinner.getSelectedItem().toString();
        TextView textView = (TextView) findViewById(R.id.titulo);


        if(a.equals("Peces")){
            try{
                String linea;
                String[] cont;
                InputStream fraw =
                        getResources().openRawResource(R.raw.peces);

                BufferedReader brin =
                        new BufferedReader(new InputStreamReader(fraw));
                linea = brin.readLine();
                while(linea!=null) {
                    cont = linea.split(",");
                    Pez nuevo = new Pez();
                    nuevo.img = cont[0];
                    nuevo.nombreCom = cont[1];
                    nuevo.nombreCien = cont[2];
                    nuevo.tamano = cont[3];
                    nuevo.habitat = cont [4];
                    datos.add(nuevo);
                    linea = brin.readLine();
                };
                fraw.close();
            }
            catch (Exception e){
                Log.v("error","he explotao...");
            }
        }
        else{
            try{
                String linea;
                String[] cont;
                InputStream fraw =
                        getResources().openRawResource(R.raw.peces);

                BufferedReader brin =
                        new BufferedReader(new InputStreamReader(fraw));
                linea = brin.readLine();
                while(linea!=null) {
                    cont = linea.split(",");
                    Pez nuevo = new Pez();
                    nuevo.img = cont[0];
                    nuevo.nombreCom = cont[1];
                    nuevo.nombreCien = cont[3];
                    nuevo.tamano = cont[4];
                    nuevo.habitat = cont [5];
                    datos.add(nuevo);
                    linea = brin.readLine();
                };
                fraw.close();
            }
            catch (Exception e){
                Log.v("error","he explotao...");
            }
        }

        Log.v("spinner",a);

    }
}
