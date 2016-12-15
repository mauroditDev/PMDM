package com.example.alumno.examen_mauro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listadoPrincipal;
    private ArrayList<Libro> datos = new ArrayList<>();
    private AdaptadorLibro adaptadorLibro;
    private Activity ppal;
    private Spinner spinner;


    private DBmanager dBmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ppal = this;

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(ppal,R.array.itemizado,
                R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id){
                construirLista();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                //cargar(0)
            }
        });


        final Context context = this.getApplicationContext();

        listadoPrincipal = (ListView) findViewById(R.id.lista);

        dBmanager = new DBmanager(context);

        construirLista();
    }

    private void construirLista(){
        datos = dBmanager.getTodos(spinner.getSelectedItem().toString());
        refrescarLista();
    }

    private void refrescarLista(){
        adaptadorLibro = new AdaptadorLibro(this,datos);
        listadoPrincipal.setAdapter(adaptadorLibro);

        final Activity actividad = this;

        listadoPrincipal.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Libro lib = (Libro)listadoPrincipal.getItemAtPosition(position);
                if(lib.favorito == 1){
                    lib.favorito = 0;
                    Toast.makeText(actividad, "Marcado como NO favorito", Toast.LENGTH_SHORT).show();
                }
                else {
                    lib.favorito = 1;
                    Toast.makeText(actividad, "Marcado como favorito", Toast.LENGTH_SHORT).show();
                }
                adaptadorLibro.notifyDataSetChanged();
            }
        });


        registerForContextMenu(listadoPrincipal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.MenuAdd:
                dBmanager.guardarFavs(datos);

                Toast.makeText(ppal, "Libros favoritos Guardados",
                        Toast.LENGTH_SHORT).show();

                construirLista();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    // Método donde definimos el menú contextual cuando se despliega
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Inflador del menú contextual
        MenuInflater inflater = getMenuInflater();

        // Si el componente que vamos a dibujar es el ListView usamos
        // el fichero XML correspondiente
        if(v.getId() == R.id.lista)
        {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;
            Libro sel = (Libro)listadoPrincipal.getAdapter().getItem(info.position);
            // Definimos la cabecera del menú contextual
            menu.setHeaderTitle(sel.titulo);
            // Inflamos el menú contextual
            inflater.inflate(R.menu.menu_contxt, menu);
            if(sel.portada == 1)
                menu.getItem(0).setTitle("Ver Portada");
            else
                menu.getItem(0).setTitle("No tiene portada");

        }
    }

    @Override
    // Si el usuario selecciona una opción del menú contextual mostramos
    // la opción seleccionada en la etiqueta
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Libro sel = (Libro)listadoPrincipal.getAdapter().getItem(info.position);
        if(sel.portada == 1)
            item.setTitle("Ver Portada");
        else
            item.setTitle("No tiene portada");

        if(item.getItemId()==R.id.portada){
           //TODO: intent imagen
            if(sel.portada==1) {
                final Activity actividad = this;
                Intent i = new Intent(actividad, Portada.class);
                String nombre = "p" + String.valueOf(sel.id);
                int portada = getResources().getIdentifier(nombre, "drawable",
                        getApplicationContext().getPackageName());
                System.out.print(portada);
                i.putExtra("nombre", nombre);
                startActivity(i);
            }
        }
        return true;
    }

}
