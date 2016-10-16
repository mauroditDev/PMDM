package com.example.alumno.listadeportes;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AdaptadorOpciones adaptador;
    Opcion[] opciones;
    ListView listaOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaOpciones = (ListView) findViewById(R.id.ListaOpciones);

        opciones = new Opcion[7];
        opciones[0] = new Opcion(R.drawable.atletismo, getString(R.string.atletismo), false);
        opciones[1] = new Opcion(R.drawable.baloncesto, getString(R.string.basket), false);
        opciones[2] = new Opcion(R.drawable.futbol, getString(R.string.futbol), false);
        opciones[3] = new Opcion(R.drawable.golf, getString(R.string.golf), false);
        opciones[4] = new Opcion(R.drawable.motociclismo, getString(R.string.moto), false);
        opciones[5] = new Opcion(R.drawable.natacion, getString(R.string.natacion), false);
        opciones[6] = new Opcion(R.drawable.pingpong, getString(R.string.pingpong), false);

        if(savedInstanceState!=null){
            opciones[0].setChk(savedInstanceState.getBoolean(STATE_ATLETISMO));
            opciones[1].setChk(savedInstanceState.getBoolean(STATE_BALONCESTO));
            opciones[2].setChk(savedInstanceState.getBoolean(STATE_FUTBOL));
            opciones[3].setChk(savedInstanceState.getBoolean(STATE_GOLF));
            opciones[4].setChk(savedInstanceState.getBoolean(STATE_MOTOS));
            opciones[5].setChk(savedInstanceState.getBoolean(STATE_NATACION));
            opciones[6].setChk(savedInstanceState.getBoolean(STATE_PINGPONG));
        }

        adaptador = new AdaptadorOpciones(this, opciones);

        listaOpciones.setAdapter(adaptador);

        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                ((Opcion) a.getItemAtPosition(position)).setChk(!((Opcion) a.getItemAtPosition(position)).getChk());
                adaptador.notifyDataSetChanged();

            }
        });


    }


    public void aceptar(View v){
        //recorre el array viendo cuál está seleccionada

        String resultado = new String();
        resultado = getResources().getString(R.string.inicio_texto);
        ArrayList<String> els = new ArrayList<>();
        for(int i = 0; i<opciones.length; i++){
            Opcion o = (Opcion)listaOpciones.getItemAtPosition(i);
            if(o.getChk()){
                els.add(o.getNombre());
            }
        }
        switch(els.size()){
            case 0:
                resultado = getResources().getString(R.string.nadaSel);
                break;
            case 1:
                resultado += " " + els.get(0);
                break;
            default:
                for(int i = 0; i<els.size(); i++){
                    resultado += " ";
                    if(i == els.size()-1){
                        resultado += getResources().getString(R.string.and) + " ";
                    }
                    resultado += els.get(i);
                    if (i < els.size()-2){
                        resultado += ",";
                    }
                }
        }


        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        boolean[] valores = new boolean[opciones.length];

        for (int i = 0; i<opciones.length ; i++){
            Opcion o = (Opcion)listaOpciones.getItemAtPosition(i);
            valores[i] = o.getChk();
        }

        savedInstanceState.putBoolean(STATE_ATLETISMO,valores[0]);
        savedInstanceState.putBoolean(STATE_BALONCESTO,valores[1]);
        savedInstanceState.putBoolean(STATE_FUTBOL,valores[2]);
        savedInstanceState.putBoolean(STATE_GOLF,valores[3]);
        savedInstanceState.putBoolean(STATE_MOTOS,valores[4]);
        savedInstanceState.putBoolean(STATE_NATACION,valores[5]);
        savedInstanceState.putBoolean(STATE_PINGPONG,valores[6]);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private final String STATE_ATLETISMO = "estadoAtletismo";
    private final String STATE_BALONCESTO = "estadoBaloncesto";
    private final String STATE_FUTBOL = "estadoFutbol";
    private final String STATE_GOLF = "estadoGolf";
    private final String STATE_MOTOS = "estadoMotos";
    private final String STATE_NATACION = "estadoNatacion";
    private final String STATE_PINGPONG = "estadoPingpong";
}
