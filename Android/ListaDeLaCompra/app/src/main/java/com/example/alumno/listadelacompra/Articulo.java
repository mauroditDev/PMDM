package com.example.alumno.listadelacompra;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alumno on 20/10/2016.
 */

public class Articulo implements Parcelable{

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(nombre);
        if(comprado)
            out.writeInt(1);
        else
            out.writeInt(0);
    }

    public static final Parcelable.Creator<Articulo> CREATOR
            = new Parcelable.Creator<Articulo>() {
        public Articulo createFromParcel(Parcel in) {
            return new Articulo(in);
        }

        public Articulo[] newArray(int size) {
            return new Articulo[size];
        }
    };

    private Articulo(Parcel in) {
        this.nombre = in.readString();
        if(in.readInt() == 1)
            this.comprado = true;
        else
            this.comprado = false;
    }

    private String nombre;
    private boolean comprado;
    public Articulo(String nombre, boolean comprado){
        this.nombre = nombre;
        this.comprado = comprado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isComprado() {
        return comprado;
    }
    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }

}