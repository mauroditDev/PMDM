package com.example.alumno.peces;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Alumno on 10/11/2016.
 */

public class AdaptadorPez extends ArrayAdapter<Pez> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual

    AdaptadorPez(Activity contexto, ArrayList<Pez> datos) {

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
            holder.nombre = (TextView) item.findViewById(R.id.nombre);
            holder.imagen = (ImageView) item.findViewById(R.id.imagen_pez);
            holder.nombreCien = (TextView) item.findViewById(R.id.nombre_cient);
            holder.tamano = (TextView) item.findViewById(R.id.dimen);
            holder.habitat = (TextView) item.findViewById(R.id.habitat);


            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }

        //Mediante getItem cargamos cada uno de los objetos del array
        Pez mielemento = getItem(position);
        holder.nombre.setText(mielemento.nombreCom);
        holder.nombreCien.setText(mielemento.nombreCien);
        holder.habitat.setText(mielemento.habitat);
        holder.tamano.setText(mielemento.tamano);
        int img = contexto.getResources().getIdentifier("@drawable/"+mielemento.img,null,contexto.getPackageName());
        holder.imagen.setImageResource(img);

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }

    class ViewHolder {
        TextView nombre;
        ImageView imagen;
        TextView nombreCien;
        TextView tamano;
        TextView habitat;
    }
}
