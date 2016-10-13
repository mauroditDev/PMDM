package com.example.alumno.ejercicioclase1;

/**
 * Created by usuario on 04/10/2016.
 */

public class Opcion {
    // Cada opcion tiene un titulo y un subtitulo
    private String titulo;
    private String subtitulo;

    public Opcion(String titulo, String subtitulo){
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }

}
