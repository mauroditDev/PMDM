package com.example.alumno.swipepeces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Alumno on 15/12/2016.
 */

public class PecesPagerAdapter extends FragmentPagerAdapter {


    public PecesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PecesFragment();
        Bundle args = new Bundle();




        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
