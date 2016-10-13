package com.example.alumno.listadeportes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaOpciones = (ListView)findViewById(R.id.ListaOpciones);

        Opcion[] opciones = new Opcion[7];
        opciones[0] = new Opcion(R.drawable.atletismo,getString(R.string.atletismo),false);
        opciones[1] = new Opcion(R.drawable.baloncesto,getString(R.string.basket),false);
        opciones[2] = new Opcion(R.drawable.futbol,getString(R.string.futbol),false);
        opciones[3] = new Opcion(R.drawable.golf,getString(R.string.golf),false);
        opciones[4] = new Opcion(R.drawable.motociclismo,getString(R.string.moto),false);
        opciones[5] = new Opcion(R.drawable.natacion, getString(R.string.natacion),false);
        opciones[6] = new Opcion(R.drawable.pingpong,getString(R.string.pingpong),false);


        AdaptadorOpciones adaptador = new AdaptadorOpciones(this,opciones);

        listaOpciones.setAdapter(adaptador);

        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });

    }
}
