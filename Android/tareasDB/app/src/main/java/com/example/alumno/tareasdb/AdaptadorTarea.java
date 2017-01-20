package com.example.alumno.tareasdb;
/**
 * Created by Alumno on 20/10/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AdaptadorTarea extends ArrayAdapter<Tarea> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual

    AdaptadorTarea(Activity contexto, ArrayList<Tarea> datos) {

        // Llamamos al constructor de la clase superior
        //se le pasa el xml que genera la fila y el array de objetos
        super(contexto, R.layout.layout_lista, datos);
        this.contexto = contexto;
    }

    // Metodo que dibuja la Vista de cada Opcion
    // Se invoca cada vez que haya que mostrar un elemento de la lista.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;
        if (item == null) {

            LayoutInflater inflater = contexto.getLayoutInflater();
            item = inflater.inflate(R.layout.layout_lista, null);

            holder = new ViewHolder();
            holder.titulo = (TextView) item.findViewById(R.id.tituloTarea);
            holder.categoria = (TextView) item.findViewById(R.id.categoriaTarea);

            item.setTag(holder);

        } else {
            holder = (ViewHolder) item.getTag();
        }

        //Mediante getItem cargamos cada uno de los objetos del array
        Tarea mielemento = getItem(position);

        holder.titulo.setText(mielemento.titulo);
        holder.categoria.setText(mielemento.categoria);

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }


    class ViewHolder {
        TextView titulo;
        TextView categoria;
    }
}