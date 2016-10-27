package com.example.alumno.listadelacompra;

/**
 * Created by Alumno on 20/10/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class NuevoDFragment extends DialogFragment {

    public interface ShareDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String newName);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    ShareDialogListener mListener;
    private Handler mResponseHandler;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ShareDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ShareDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_share, null);

        final EditText texto = (EditText) v.findViewById(R.id.textoIntroducido);
        texto.selectAll();

        builder.setView(v)
                .setTitle(R.string.alta)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = texto.getText().toString();
                        mListener.onDialogPositiveClick(NuevoDFragment.this, name);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(NuevoDFragment.this);
                        NuevoDFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }



}