package com.example.alumno.examen_mauro;

/**
 * Created by Alumno on 14/12/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Alumno on 01/12/2016.
 */

public class DBmanager {

    private SQLiteDatabase db;

    public DBmanager(Context activity){
        ExamenSQLiteHelper usdbh = new ExamenSQLiteHelper(activity, "DBUsuarios.db", null, 1);


        db = usdbh.getWritableDatabase();


    }

    public ArrayList<Libro> getTodos(){

        ArrayList<Libro> res = new ArrayList<>();

        Cursor cursor = db.rawQuery("select _id,TITULO,AUTOR,PORTADA,FAVORITO from LIBROS " +
                "order by AUTOR desc",
                null
        );
        while(cursor.moveToNext()) {
            res.add(new
                    Libro(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("TITULO")),
                    cursor.getString(cursor.getColumnIndex("AUTOR")),
                    cursor.getInt(cursor.getColumnIndex("PORTADA")),
                    cursor.getInt(cursor.getColumnIndex("FAVORITO")))
            );
        }

        return res;
    }
    public ArrayList<Libro> getTodos(String orden){
        System.out.println(orden);

        ArrayList<Libro> res = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT _id,TITULO,AUTOR,PORTADA,FAVORITO FROM LIBROS " +
                        "ORDER BY "+orden.toUpperCase()+" ASC",
                null
        );
        while(cursor.moveToNext()) {
            res.add(new
                    Libro(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("TITULO")),
                    cursor.getString(cursor.getColumnIndex("AUTOR")),
                    cursor.getInt(cursor.getColumnIndex("PORTADA")),
                    cursor.getInt(cursor.getColumnIndex("FAVORITO")))
            );
        }

        return res;
    }

    public void updateLibro(Libro libro){
        String sql = "UPDATE LIBROS SET FAVORITO = "+libro.favorito+
                " WHERE _id = "+libro.id;
        db.execSQL(sql);
    }

    public int getFav(Libro libro){
        String sql = "SELECT FAVORITO FROM LIBROS WHERE _id = "+libro.id;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex("FAVORITO"));
        }
        return -1;
    }

    public void guardarFavs(ArrayList<Libro> datos) {
        for (Libro libro:
             datos ) {
            if(getFav(libro)!=libro.favorito)
                updateLibro(libro);
        }
    }
}
