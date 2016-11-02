package com.example.alumno.listadeportes;

/**
 * Created by Alumno on 13/10/2016.
 */

public class Opcion {
    private int imagen;
    private String nombre;
    private boolean chk;

    public int getImagen(){
        return this.imagen;
    }
    public void setImagen(int img){
        this.imagen = img;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public boolean getChk(){
        return this.chk;
    }
    public void setChk(boolean chk){
        this.chk =chk;
    }

    public Opcion(int imagen, String nombre, boolean chk){
        this.chk = chk;
        this.imagen = imagen;
        this.nombre = nombre;
    }

}
