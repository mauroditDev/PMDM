package com.example.alumno.tareasdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Alumno on 01/12/2016.
 */

public class DBmanager {

    private SQLiteDatabase db;

    public DBmanager(Context activity){
        TareasSQLiteHelper usdbh = new TareasSQLiteHelper(activity, "DBUsuarios.db", null, 1);

        db = usdbh.getWritableDatabase();

    }

    public ArrayList<Tarea> getTodos(){
        ArrayList<Tarea> res = new ArrayList<>();

        Cursor cursor = db.rawQuery("select _id, categoria, titulo, descripcion from tareas",
                null
        );
        while(cursor.moveToNext()) {
            res.add(new
                    Tarea(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("categoria")),
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("descripcion")))
            );
        }

        return res;
    }

    public void updateTarea(Tarea tarea){
        String sql = "UPDATE tareas SET categoria = '"+tarea.categoria+
                "', titulo = '"+tarea.titulo+
                "', descripcion = '"+tarea.descripcion+
                "' WHERE _id = "+tarea._id;
        db.execSQL(sql);
    }

    public void insertTarea(Tarea tarea){
        String sql = "INSERT INTO tareas (categoria, titulo, descripcion) VALUES ("+
                "'"+tarea.categoria+"','"+tarea.titulo+"','"+tarea.descripcion+"')";
        db.execSQL(sql);
    }

    public void eliminarTarea(Tarea tarea) {
        String sql = "DELETE FROM tareas"+
                " WHERE _id = "+tarea._id;
        db.execSQL(sql);
    }
}

