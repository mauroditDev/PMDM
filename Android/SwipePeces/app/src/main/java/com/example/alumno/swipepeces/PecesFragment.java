package com.example.alumno.swipepeces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alumno on 15/12/2016.
 */

public class PecesFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        Bundle args = getArguments();
        ImageView image = (ImageView)rootView.
                findViewById(R.id.imageView);

        ArrayList<Integer> imgIds = new ArrayList<>();


        image.setImageResource(imgIds.get(args.getInt("imagen")));


        ((TextView) rootView.findViewById(R.id.nombre)).setText(
                getString(args.getInt("nombre")));
        ((TextView) rootView.findViewById(R.id.nombre_cient)).setText(
                getString(args.getInt("nombre_cient")));
        ((TextView) rootView.findViewById(R.id.dimen)).setText(
                getString(args.getInt("dimen")));
        ((TextView) rootView.findViewById(R.id.habitat)).setText(
                getString(args.getInt("habitat")));

        return rootView;
    }

}
