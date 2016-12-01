package com.example.alumno.tareasdb;

/**
 * Created by Alumno on 01/12/2016.
 */

public class Tarea {
    public int _id;
    public String categoria;
    public String titulo;
    public String descripcion;

    public Tarea(int id, String cat, String tit, String des){
        _id = id;
        categoria = cat;
        titulo = tit;
        descripcion = des;
    }

}
