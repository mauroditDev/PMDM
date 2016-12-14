package com.example.alumno.examen_mauro;

/**
 * Created by Alumno on 14/12/2016.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdaptadorLibro extends ArrayAdapter<Libro> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual

    AdaptadorLibro(Activity contexto, ArrayList<Libro> datos) {

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
            holder.titulo = (TextView) item.findViewById(R.id.tituloLibro);
            holder.autor = (TextView) item.findViewById(R.id.autorLibro);
            holder.portada = (ImageView) item.findViewById(R.id.portada);

            item.setTag(holder);

        } else {
            holder = (ViewHolder) item.getTag();
        }

        //Mediante getItem cargamos cada uno de los objetos del array
        Libro mielemento = getItem(position);

        holder.titulo.setText(mielemento.titulo);
        holder.autor.setText(mielemento.autor);
        if(mielemento.portada == 1){
            holder.portada.setImageResource(R.mipmap.ic_portada);
        }
        else{
            holder.portada.setImageResource(R.mipmap.ic_sin_portada);
        }

        if (mielemento.favorito == 1) {
            holder.titulo.setPaintFlags(holder.titulo.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            holder.titulo.setTextColor(Color.parseColor("#00FF00"));
        } else {
            holder.titulo.setPaintFlags(holder.titulo.getPaintFlags()
                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.titulo.setTextColor(Color.parseColor("#FF0000"));
        }

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }


    class ViewHolder {
        TextView titulo;
        TextView autor;
        ImageView portada;
    }
}