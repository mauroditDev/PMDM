package com.example.alumno.peces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Imagen extends AppCompatActivity {

    public Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        data = this.getIntent();

        ImageView imageView = (ImageView) findViewById(R.id.fotaco);
        int img = data.getExtras().getInt("imagen");
        imageView.setImageResource(img);

    }
}
