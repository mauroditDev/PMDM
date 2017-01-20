package com.example.alumno.swipepeces;

import android.support.v7.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener{

    PecesPagerAdapter pecesPagerAdapter;
    ViewPager viewpager;
    ArrayList<Peces> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtener instancia de la Action Bar
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        // Android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        // Activar el modo de navegación con tabs en la Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Deshabilitar el caret Up del icono de la aplicación
        actionBar.setHomeButtonEnabled(false);

        // Crear adaptador de fragmentos
        pecesPagerAdapter = new PecesPagerAdapter(getSupportFragmentManager());

        datos = new ArrayList<>();
        String linea;
        String[] cont;
        InputStream fraw;
        BufferedReader brin;
            fraw = getResources().openRawResource(R.raw.peces);
            brin = new BufferedReader(new InputStreamReader(fraw));

        try{

            linea = brin.readLine();
            while(linea!=null) {
                cont = linea.split(",");
                Peces nuevo = new Peces();

                nuevo.img = cont[0];
                nuevo.nombreCom = cont[1];
                nuevo.nombreCien = cont[2];
                nuevo.tamano = cont[3];
                nuevo.habitat = cont[4];

                datos.add(nuevo);
                linea = brin.readLine();
            }
            brin.close();
            fraw.close();

            fraw = getResources().openRawResource(R.raw.algaseinvertebrados);
            brin = new BufferedReader(new InputStreamReader(fraw));
            linea = brin.readLine();
            while(linea!=null) {
                cont = linea.split(",");
                Peces nuevo = new Peces();

                nuevo.img = cont[0];
                nuevo.nombreCom = cont[1];
                nuevo.nombreCien = cont[3];
                nuevo.tamano = cont[4];
                nuevo.habitat = cont[5];

                datos.add(nuevo);
                linea = brin.readLine();
            }
            brin.close();
            fraw.close();

        }catch(Exception exc){
            exc.printStackTrace();
        }

        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(pecesPagerAdapter);
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Coordinar el item del pager con la pestaña
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // Añadir 3 pestañas y asignarles un título y escucha
        for (int i = 0; i < pecesPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(pecesPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
}
