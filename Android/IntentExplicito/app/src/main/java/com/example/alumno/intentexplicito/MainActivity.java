package com.example.alumno.intentexplicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int contactos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            contactos = savedInstanceState.getInt("contactos");
        }
        Button btnAlta = (Button) findViewById(R.id.btn_alta);
        Button btnMod = (Button) findViewById(R.id.btn_modificacion);
        TextView tvNombre = (TextView) findViewById(R.id.nombre);
        TextView tvApellido = (TextView) findViewById(R.id.apellido);
        if ( contactos == 0 ){
            btnMod.setEnabled(false);
            btnAlta.setEnabled(true);
        }
        else {
            btnMod.setEnabled(true);
            btnAlta.setEnabled(false);
            tvNombre.setText(savedInstanceState.getString("nombre"));
            tvApellido.setText(savedInstanceState.getString("apellido"));
        }
    }

    public void alta(View v){
        Intent i = new Intent(this, SecondaryActivity.class);
        i.putExtra("nombre", "");
        i.putExtra("apellido", "");
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
        contactos = 1;
    }

    public void mod(View v){
        TextView nom = (TextView) findViewById(R.id.nombre);
        TextView ape = (TextView) findViewById(R.id.apellido);
        String apellido = ape.getText().toString();
        String nombre = nom.getText().toString();
        apellido.replace(getResources().getString(R.string.apell),"");
        nombre.replace(getResources().getString(R.string.nombre),"");
        Intent i = new Intent(this, SecondaryActivity.class);
        i.putExtra("nombre", nombre);
        i.putExtra("apellido", apellido);
        startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String nombre = getResources().getString(R.string.nombre)+data.getExtras().getString("nombre");
        String apellido = getResources().getString(R.string.apell)+data.getExtras().getString("apellido");

        TextView tvNombre = (TextView) findViewById(R.id.nombre);
        TextView tvApellido = (TextView) findViewById(R.id.apellido);

        if(resultCode == RESULT_OK) {
            tvNombre.setText(nombre);
            tvApellido.setText(apellido);

            Button btnAlta = (Button) findViewById(R.id.btn_alta);
            Button btnMod = (Button) findViewById(R.id.btn_modificacion);

            btnMod.setEnabled(true);
            btnAlta.setEnabled(false);

        }
        else{
            if(data.getExtras().getString("nombre").length()==0){
                /*Toast.makeText(this,getResources().getString(R.string.comprado)+" "+
                                ((Articulo) a.getItemAtPosition(position)).getNombre(),
                        Toast.LENGTH_SHORT).show();*/
            }
            else{
                /*Toast.makeText(context,getResources().getString(R.string.comprado)+" "+
                                ((Articulo) a.getItemAtPosition(position)).getNombre(),
                        Toast.LENGTH_SHORT).show();*/
            }
        }

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {

        TextView tvNombre = (TextView) findViewById(R.id.nombre);
        TextView tvApellido = (TextView) findViewById(R.id.apellido);

        savedInstanceState.putInt("contactos",contactos);
        savedInstanceState.putString("nombre",tvNombre.getText().toString());
        savedInstanceState.putString("apellido",tvApellido.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
