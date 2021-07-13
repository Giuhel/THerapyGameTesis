package com.helio.therapygame.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.interfaces.IComunicaFragments;


public class CopaFragment extends DialogFragment {

    Activity actividad;
    IComunicaFragments iComunicaFragments;

    ImageButton btnSalir;
    LinearLayout barraSuperior;
    CardView cardaceptar;

    public CopaFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoGestionarJuego();
    }

    private AlertDialog crearDialogoGestionarJuego() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_copa, null);
        builder.setView(v);

        barraSuperior=v.findViewById(R.id.barraSuperior);
        btnSalir=v.findViewById(R.id.btnSalir);
        cardaceptar=v.findViewById(R.id.cardAceptar);

        eventosBotones();

        return builder.create();
    }

    private void eventosBotones() {
        cardaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void asignarValoresPreferencias(){
        barraSuperior.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }
}