package com.example.alumno.examen_mauro;

/**
 * Created by Alumno on 14/12/2016.
 */

public class Libro {
    /*_id INTEGER PRIMARY KEY,TITULO TEXT, AUTOR TEXT, PORTADA INTEGER, FAVORITO INTEGER*/
    public int id;
    public String titulo;
    public String autor;
    public int portada;
    public int favorito;

    public Libro(int id, String titulo, String autor, int portada, int favorito){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.portada = portada;
        this.favorito = favorito;
    }

}
