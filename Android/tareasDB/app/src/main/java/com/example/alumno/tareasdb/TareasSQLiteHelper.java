package com.example.alumno.tareasdb;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TareasSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE tareas (\n" +
            "_id INTEGER PRIMARY KEY,\n" +
            "categoria TEXT NOT NULL,\n" +
            "titulo TEXT NOT NULL,\n" +
            "descripcion TEXT NOT NULL\n" +
            ")";

    public TareasSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creaci?n de la tabla
        db.execSQL(sqlCreate);

        db.execSQL("INSERT INTO tareas (categoria, titulo, descripcion)" +
                " VALUES(" +
                "'Cita / Reunión','Reunión con Miguel'," +
                "'Reunión para la venta del jueves'" +
                ")");

        db.execSQL("INSERT INTO tareas (categoria, titulo, descripcion)" +
                " VALUES(" +
                "'Varios','Llamar a María'," +
                "'El jueves empezó un trabajo'" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aqu? utilizamos directamente
        //      la opci?n de eliminar la tabla anterior y crearla de nuevo
        //      vac?a con el nuevo formato.
        //      Sin embargo lo normal ser? que haya que migrar datos de la
        //      tabla antigua a la nueva, por lo que este m?todo deber?a
        //      ser m?s elaborado.

        //Se elimina la versi?n anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versi?n de la tabla
        db.execSQL(sqlCreate);
    }

}