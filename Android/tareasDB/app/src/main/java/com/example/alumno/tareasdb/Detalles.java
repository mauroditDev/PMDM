package com.example.alumno.tareasdb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class Detalles extends AppCompatActivity {

    Intent data;
    EditText nombreTarea;
    EditText descripcionTarea;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_layout);

        data = getIntent();

        spinner = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.itemizado,R.layout.spinner_tipo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int position = 0;

        switch(data.getExtras().getString("tipo")){
            case "Cita / Reunión":
                position = 0;
                break;
            case "Cosas pendiente":
                position = 1;
                break;
            case "Varios":
                position = 2;
        }
        spinner.setSelection(position);

        nombreTarea = (EditText) findViewById(R.id.nombre);
        nombreTarea.setText(data.getExtras().getString("nombre"));

        descripcionTarea = (EditText) findViewById(R.id.descripcion);
        descripcionTarea.setText(data.getExtras().getString("descripcion"));

    }

    public void aceptar(View v){

        Intent respuesta = new Intent();

        respuesta.putExtra("nombre",nombreTarea.getText().toString());
        respuesta.putExtra("descripcion",descripcionTarea.getText().toString());
        respuesta.putExtra("tipo",spinner.getSelectedItem().toString());
        respuesta.putExtra("id",data.getExtras().getInt("id"));

        setResult(RESULT_OK, respuesta);

        finish();
    }

}
