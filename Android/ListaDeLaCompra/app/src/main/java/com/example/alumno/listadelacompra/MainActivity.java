package com.example.alumno.listadelacompra;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NuevoDFragment.ShareDialogListener, EditDFragment.ShareDialogListener{

    private ListView listadoPrincipal;
    // Definimos el adaptador que va a usar el ListView
    // Matriz con los datos del adaptador
    private ArrayList<Articulo> datos = new ArrayList<Articulo>();
    private AdaptadorArticulo adaptadorArticulo;


    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listadoPrincipal = (ListView) findViewById(R.id.lista);

        if(savedInstanceState == null){
            datos.add(new Articulo(getResources().getString(R.string.ex1),false));
            datos.add(new Articulo(getResources().getString(R.string.ex2),false));
            datos.add(new Articulo(getResources().getString(R.string.ex3),false));
        }

        adaptadorArticulo = new AdaptadorArticulo(this,datos);
        listadoPrincipal.setAdapter(adaptadorArticulo);

        listadoPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ((Articulo)a.getItemAtPosition(position)).
                        setComprado(!((Articulo) a.getItemAtPosition(position)).isComprado());
                adaptadorArticulo.notifyDataSetChanged();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.MenuAdd:
                NuevoDFragment dialog = new NuevoDFragment();
                dialog.show(fm, "NuevoDFragment");
                return true;
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
            // Definimos la cabecera del menú contextual
            menu.setHeaderTitle(
                    ((Articulo)listadoPrincipal.getAdapter().getItem(info.position)).getNombre());
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

        switch (item.getItemId()) {

            // Se selecciona la opción "Borrar Elemento" de menú contextual de la etiqueta
            case R.id.borrar:
                //TODO: (opcional) sacar el cartel de "seguro que desea borrar?"
                datos.remove(info.position);
                adaptadorArticulo.notifyDataSetChanged();
                return true;

            // Se selecciona la opción "Editar elemento" de menú contextual de la etiqueta
            case R.id.editar:
                String nombreaeditar=datos.get(info.position).getNombre();
                EditDFragment editarDFragment= new EditDFragment();
                Bundle bundle = new Bundle(2);
                bundle.putString("nombreaeditar", nombreaeditar);
                bundle.putInt("posicion", info.position);
                editarDFragment.setArguments(bundle);
                editarDFragment.show(fm, "NuevoDFragment");

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onDialogPositiveClick(
            android.support.v4.app.DialogFragment dialog,String newName) {
        datos.add(new Articulo(newName,false));
        adaptadorArticulo.notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(
            android.support.v4.app.DialogFragment dialog,String newName, int position) {
        datos.get(position).setNombre(newName);
        adaptadorArticulo.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(android.support.v4.app.DialogFragment dialog) {

    }
}
