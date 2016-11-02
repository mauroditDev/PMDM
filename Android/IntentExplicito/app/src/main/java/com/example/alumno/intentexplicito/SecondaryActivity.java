package com.example.alumno.intentexplicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        data = this.getIntent();
        TextView title = (TextView) findViewById(R.id.sec_title);
        EditText nombre = (EditText) findViewById(R.id.edit_nombre);
        EditText apellido = (EditText) findViewById(R.id.edit_apell);
        if(data.getExtras().getString("nombre").length()>0) {
            title.setText(getResources().getString(R.string.sec_title_m));
            nombre.setText(data.getExtras().getString("nombre"));
            apellido.setText(data.getExtras().getString("apellido"));
        }
        else
            title.setText(getResources().getString(R.string.sec_title_a));

    }

    public void aceptar(View v){
        EditText nombre = (EditText) findViewById(R.id.edit_nombre);
        EditText apellido = (EditText) findViewById(R.id.edit_apell);

        Intent respuesta = new Intent();

        respuesta.putExtra("nombre", nombre.getText().toString());
        respuesta.putExtra("apellido", apellido.getText().toString());

        setResult(RESULT_OK, respuesta);

        finish();
    }



}
