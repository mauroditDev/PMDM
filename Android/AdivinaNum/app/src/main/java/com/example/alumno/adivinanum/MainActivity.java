package com.example.alumno.adivinanum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

            TextView textViex = (TextView) findViewById(R.id.mainText);
            Button button = (Button)findViewById(R.id.mainBtn);

            ganado = savedInstanceState.getBoolean(STATE_GANADO);
            trys = savedInstanceState.getInt(STATE_TRYS);
            search = savedInstanceState.getInt(STATE_SEARCH);
            textViex.setText(savedInstanceState.getString(STATE_MENSAJE));
            button.setText(savedInstanceState.getString(STATE_BOTON));

        }
        else{
            reset();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView textViex = (TextView) findViewById(R.id.mainText);
        Button button = (Button)findViewById(R.id.mainBtn);

        // Save the user's current game state
        savedInstanceState.putBoolean(STATE_GANADO,ganado);
        savedInstanceState.putInt(STATE_SEARCH,search);
        savedInstanceState.putInt(STATE_TRYS,trys);
        savedInstanceState.putString(STATE_MENSAJE, textViex.getText().toString());
        savedInstanceState.putString(STATE_BOTON, button.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void jugar(View v){

        Button button = (Button)v;
        EditText textInput = (EditText)findViewById(R.id.textInput);
        TextView mainText = (TextView)findViewById(R.id.mainText);

        if(ganado){
            reset();
            mainText.setText(R.string.textoInicial);
            button.setText(R.string.botonTry);
        }
        else {
            if (textInput.getText().toString().length() > 0 && !(textInput.getText().toString().equals(""))) {
                int test = Integer.parseInt(textInput.getText().toString());
                if(comprobar(test)){
                    button.setText(R.string.botonRestart);
                    textInput.setVisibility(View.INVISIBLE);
                }
            }
        }

    }

    public void reset(){
        EditText editText = (EditText) findViewById(R.id.textInput);
        trys = 0;
        search = (int)(Math.random()*100+1);
        ganado = false;
        editText.setVisibility(View.VISIBLE);
    }

    public boolean comprobar(int test){
        TextView mainText = (TextView)findViewById(R.id.mainText);
        TextView trysText = (TextView)findViewById(R.id.trysText);
        if (test < search) {
            mainText.setText(String.format(getResources().getString(R.string.textoMayor), test));
            trys++;
            trysText.setText(String.format(getResources().getString(R.string.intentos), trys));
        } else {
            if (test > search) {
                mainText.setText(String.format(getResources().getString(R.string.textoMenor), test));
                trys++;
                trysText.setText(String.format(getResources().getString(R.string.intentos), trys));
            } else {
                ganado = true;
                trys++;
                mainText.setText(String.format(getResources().getString(R.string.textoGenar), trys));
            }
        }
        return ganado;
    }


    private final  String STATE_GANADO = "estadoGanado";
    private final  String STATE_TRYS = "numIntentos";
    private final  String STATE_SEARCH = "numBuscado";
    private final  String STATE_MENSAJE = "mensajeSuperior";
    private final  String STATE_BOTON = "mensajeBoton";

    private boolean ganado;
    private int trys;
    private int search;
}
