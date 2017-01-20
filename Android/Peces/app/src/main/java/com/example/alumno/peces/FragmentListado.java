package com.example.alumno.peces;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FragmentListado extends Fragment {

    public ArrayList<Pez> datos;

    private ListView lista;
    TextView textView;
    AdaptadorPez adaptadoPeces;

    private ImagenListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        datos = new ArrayList<>();

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.itemizado,R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        textView = (TextView) getView().findViewById(R.id.titulo);

        lista = (ListView) getView().findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                if (listener!=null) {
                    listener.onItemSeleccionado(
                            (Pez)lista.getAdapter().getItem(position));
                }

            }
        });

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id){
                cargar(position);
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                //cargar(0)
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

            lista = (ListView) getView().findViewById(R.id.lista);
            adaptadoPeces = new AdaptadorPez(this.getActivity(),datos);
            lista.setAdapter(adaptadoPeces);
        }
        catch (Exception e){
            Log.v("error","he explotao...");
        }

    }


    public interface ImagenListener {
        void onItemSeleccionado(Pez pez);
    }

    public void setImagenListener(ImagenListener listener) {
        this.listener=listener;
    }


}