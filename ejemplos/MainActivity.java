package com.example.alumno.ejercicioclase1;


import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {



    private Opcion[] datos = new Opcion[32];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asignamos el objeto ListView con el id de la etiqueta ListView del
        //layout principal
        ListView listaOpciones = (ListView)findViewById(R.id.ListaOpciones);
        // Creamos los objetos y los guardamos en un array
        for(int i=1; i<=32; i++)
            datos[i-1] = new Opcion("Opción " + i, "Esta es la opción " + i);


        // Usamos un adaptador para dibujar las opciones de la lista
        AdaptadorOpciones adaptador = new AdaptadorOpciones(this,datos);

        // Establecemos el adaptador del Listview
        listaOpciones.setAdapter(adaptador);

        // Definimos el evento setOnItemClick
        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Si se hace clic sobre una opción mostramos un mensaje
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Has hecho clic en la '" + datos[position].getTitulo() + "'", Toast.LENGTH_LONG).show();
            }
        });
    }


}
