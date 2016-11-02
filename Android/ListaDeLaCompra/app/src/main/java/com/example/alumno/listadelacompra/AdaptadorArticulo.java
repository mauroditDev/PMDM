package com.example.alumno.listadelacompra;

/**
 * Created by Alumno on 20/10/2016.
 */
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.alumno.listadelacompra.R.id.nombreArticulo;

public class AdaptadorArticulo extends ArrayAdapter<Articulo> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual

    AdaptadorArticulo(Activity contexto, ArrayList<Articulo> datos) {

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
            holder.nombre = (TextView) item.findViewById(nombreArticulo);

            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }

        //Mediante getItem cargamos cada uno de los objetos del array
        Articulo mielemento = getItem(position);


        if (mielemento.isComprado()) {
            holder.nombre.setPaintFlags(holder.nombre.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            holder.nombre.setTextColor(Color.parseColor("#00FF00"));
        } else {
            holder.nombre.setPaintFlags(holder.nombre.getPaintFlags()
                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.nombre.setTextColor(Color.parseColor("#FF0000"));
        }

        holder.nombre.setText(mielemento.getNombre());

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }


    class ViewHolder {
        TextView nombre;
    }
}