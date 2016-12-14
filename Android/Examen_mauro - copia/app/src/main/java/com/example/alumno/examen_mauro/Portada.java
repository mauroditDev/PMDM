package com.example.alumno.examen_mauro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Alumno on 14/12/2016.
 */

public class Portada extends AppCompatActivity {

    Intent data;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);
        imageView = (ImageView) findViewById(R.id.portada_grande) ;

        data = getIntent();
        int img = getApplicationContext().getResources().getIdentifier(data.getExtras().
                getString("nombre"), "drawable", getApplicationContext().getPackageName());
        System.out.print(img);
        imageView.setImageResource(img);


    }

}