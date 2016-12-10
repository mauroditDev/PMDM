package com.example.alumno.tareasdb;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listadoPrincipal;
    private ArrayList<Tarea> datos = new ArrayList<>();
    private AdaptadorTarea adaptadorTarea;
    private static final int DETALLES_TAREA = 1;
    private static final int NUEVA_TAREA = 2;

    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Context context = this.getApplicationContext();

        listadoPrincipal = (ListView) findViewById(R.id.lista);

        dBmanager = new DBmanager(context);

        construirLista();


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {
            if(requestCode == DETALLES_TAREA) {
                Tarea tar = new Tarea(data.getExtras().getInt("id"), data.getExtras().getString("tipo"),
                        data.getExtras().getString("nombre"), data.getExtras().getString("descripcion"));
                dBmanager.updateTarea(tar);
                construirLista();
            }
            if(requestCode == NUEVA_TAREA){
                Tarea tar = new Tarea(data.getExtras().getInt("id"), data.getExtras().getString("tipo"),
                        data.getExtras().getString("nombre"), data.getExtras().getString("descripcion"));
                dBmanager.insertTarea(tar);
                construirLista();
            }
        }
        else{

            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Activity actividad = this;

        switch (item.getItemId()) {
            case R.id.MenuAdd:
                Intent i = new Intent(actividad, Detalles.class);
                Tarea tar = new Tarea();
                i.putExtra("nombre", tar.titulo);
                i.putExtra("descripcion", tar.descripcion);
                i.putExtra("tipo",tar.categoria);
                i.putExtra("id",tar._id);
                startActivityForResult(i, NUEVA_TAREA);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void construirLista(){
        datos = dBmanager.getTodos();

        adaptadorTarea = new AdaptadorTarea(this,datos);
        listadoPrincipal.setAdapter(adaptadorTarea);

        final Activity actividad = this;

        listadoPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent i = new Intent(actividad, Detalles.class);
                Tarea tar = (Tarea)listadoPrincipal.getItemAtPosition(position);
                i.putExtra("nombre", tar.titulo);
                i.putExtra("descripcion", tar.descripcion);
                i.putExtra("tipo",tar.categoria);
                i.putExtra("id",tar._id);
                startActivityForResult(i, DETALLES_TAREA);
            }
        });

        registerForContextMenu(listadoPrincipal);

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
            // Definimos la cabecera del menú contextual
            menu.setHeaderTitle(
                    ((Tarea)listadoPrincipal.getAdapter().getItem(info.position)).titulo);
            // Inflamos el menú contextual
            inflater.inflate(R.menu.menu_contxt, menu);
        }
    }

    @Override
    // Si el usuario selecciona una opción del menú contextual mostramos
    // la opción seleccionada en la etiqueta
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId()==R.id.borrar){
            dBmanager.eliminarTarea((Tarea)listadoPrincipal.getAdapter().getItem(info.position));
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
            construirLista();
        }
        return true;
    }

}
