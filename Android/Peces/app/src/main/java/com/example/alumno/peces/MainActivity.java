package com.example.alumno.peces;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity
        implements FragmentListado.ImagenListener{

    public ArrayList<Pez> datos;
    AdaptadorPez adaptadoPeces;
    ListView lista;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentListado frgListado =
                (FragmentListado)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        frgListado.setImagenListener(this);

    }

    @Override
    public void onItemSeleccionado(Pez pez) {
        boolean hayDetalle =
                (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);
        int img = getResources().getIdentifier("@drawable/"+pez.img,null,this.getPackageName());

        if(hayDetalle) {
            ((FragmentDetalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarDetalle(img);
        }
        else {
            imagen(img);
        }
    }


    public void imagen(int img){

        Intent i = new Intent(this, Imagen.class);
        i.putExtra("imagen",img);
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);

    }

    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
