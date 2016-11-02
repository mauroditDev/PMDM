package com.example.usuario.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


// import android.app.Activity;

import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configuramos la ventana, añadiendo un botón
        // que llamará a nuestro método protegido.
        _boton = new Button(this);
        _boton.setText("¡Púlsame!");
        _boton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        botonPulsado();
                    }
                }
        );
        // Ponemos el botón como componente de la
        // actividad.
        setContentView(_boton);
    }

    /**
     * Método llamado cuando se pulsa sobre el botón
     * de la ventana. Es llamado a través de la clase
     * anónima del evento.
     */
    private void botonPulsado() {

        // Incrementamos el contador...
        ++_numVeces;

        // ... y actualizamos la etiqueta del botón.
        _boton.setText("Pulsado " + _numVeces + " veces");

    } // botonPulsado
    /**
     * Botón de la ventana.
     */
    private Button _boton;

    /**
     * Número de veces que se ha pulsado el botón.
     */
    private int _numVeces;

}
