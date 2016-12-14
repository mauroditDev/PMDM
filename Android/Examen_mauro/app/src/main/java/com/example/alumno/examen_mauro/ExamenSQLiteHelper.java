package com.example.alumno.examen_mauro;

/**
 * Created by Alumno on 14/12/2016.
 */


        import android.app.Activity;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;

public class ExamenSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios

    public String sqlCreate;
    private Context context;

    public ExamenSQLiteHelper(Context contexto, String nombre,
                              CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        context = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("PATATA");
        String linea;
        sqlCreate = "";
        InputStream fraw;
        BufferedReader brin;
        fraw = context.getResources().openRawResource(R.raw.tituloautor);
        brin = new BufferedReader(new InputStreamReader(fraw));
        try{
            linea = brin.readLine();
            while(linea!=null){
                sqlCreate += linea+"\n";
                db.execSQL(linea);
                linea = brin.readLine();
            }
            System.out.println(sqlCreate);
            brin.close();
        }catch(Exception e){
            Log.e("error","explosion!");
            Log.v("error","explosion!");
        }

        System.out.println(sqlCreate);
        //Se ejecuta la sentencia SQL de creaci?n de la tabla


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
        db.execSQL("DROP TABLE IF EXISTS LIBROS");
        System.out.println("PATATA");
        String linea;
        sqlCreate = "";
        InputStream fraw;
        BufferedReader brin;
        fraw = context.getResources().openRawResource(R.raw.tituloautor);
        brin = new BufferedReader(new InputStreamReader(fraw));
        try {
            linea = brin.readLine();
            while (linea != null) {
                sqlCreate += linea + "\n";
                db.execSQL(linea);
                linea = brin.readLine();
            }
            System.out.println(sqlCreate);
            brin.close();
        } catch (Exception e) {
            Log.e("error", "explosion!");
            Log.v("error", "explosion!");
        }

        System.out.println(sqlCreate);
    }
}