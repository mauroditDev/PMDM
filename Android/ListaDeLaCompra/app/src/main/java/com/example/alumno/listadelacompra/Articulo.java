package com.example.alumno.listadelacompra;

/**
 * Created by Alumno on 20/10/2016.
 */

public class Articulo {
    // Cada opción tiene un título y un subtítulo
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