package com.example.alumno.milestokm;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcular(View v){
        Button button = (Button) v;
        EditText editText = (EditText)findViewById(R.id.textInput);
        TextView textView = (TextView)findViewById(R.id.result);
        RadioButton toMiles = (RadioButton)findViewById(R.id.toMiles);
        RadioButton toKm = (RadioButton)findViewById(R.id.toKm);

        if(editText.getText().toString().equals("") || editText.getText().toString().length()==0){
            Toast t = Toast.makeText(getBaseContext(),R.string.toast,Toast.LENGTH_LONG);
            t.show();
        }
        else{
            if(toMiles.isChecked()){
                double aux = (double)Double.parseDouble(editText.getText().toString());
                aux = aux/1.609;
                textView.setText(String.valueOf(aux).substring(0,String.valueOf(aux).indexOf('.')+3));
            }
            if(toKm.isChecked()){
                double aux = (double)Double.parseDouble(editText.getText().toString());
                aux = aux*1.609;
                textView.setText(String.valueOf(aux).substring(0,String.valueOf(aux).indexOf('.')+3));
            }
        }
    }

}
