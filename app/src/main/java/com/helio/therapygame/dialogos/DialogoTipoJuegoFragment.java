package com.helio.therapygame.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.helio.therapygame.R;
import com.helio.therapygame.actividades.Memorama;
import com.helio.therapygame.actividades.cantidadActivity;
import com.helio.therapygame.actividades.Nivel1Activity;
import com.helio.therapygame.actividades.Nivel2Activity;
import com.helio.therapygame.actividades.RazonamientoActivity;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.utilidades;
import com.helio.therapygame.interfaces.IComunicaFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

public class DialogoTipoJuegoFragment extends DialogFragment {

    //variables
    Activity actividad;
    IComunicaFragments iComunicaFragments;

    ImageButton btnSalir;
    LinearLayout barraSuperior;
    CardView cardFacil,cardMedio,cardLetraEscondida,cardMemoria,cardrazonamiento;

    String modo= PreferenciasJuego.modoJuego;



    public DialogoTipoJuegoFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoGestionarJuego();
    }

    private AlertDialog crearDialogoGestionarJuego() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_tipo_juego, null);
        builder.setView(v);


        barraSuperior=v.findViewById(R.id.barraSuperior);

        btnSalir=v.findViewById(R.id.btnSalir);

        cardFacil=v.findViewById(R.id.cardFacil);
        cardMedio=v.findViewById(R.id.cardMedio);
        cardLetraEscondida=v.findViewById(R.id.CardLetraEscondida);
        cardMemoria=v.findViewById(R.id.CardMemoriaFigura);
        cardrazonamiento=v.findViewById(R.id.CardRazonamiento);

        eventosBotones();

        return builder.create();

    }

    private void eventosBotones() {
        cardFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInstruccionesNivelFacil().show();
            }
        });

        cardMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInstruccionesNivelMedio().show();
            }
        });

        cardLetraEscondida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInstruccionesLetraEscondida().show();
            }
        });

        cardMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInstruccionesMemoria().show();
            }
        });

        cardrazonamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInstruccionesRazonamiento().show();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public AlertDialog dialogoInstruccionesNivelFacil(){
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setTitle("NIVEL FACIL")
                .setMessage("Presiones los botones dependiendo de la palabra y el color presentado, "+
                        "por cada acierto se obtendra un puntaje")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //no hacemos nada
                            }
                        })
                .setPositiveButton("JUGAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                utilidades.nivelJuego=1;
                                Intent intent=new Intent(actividad, Nivel1Activity.class);
                                startActivity(intent);
                                dismiss();
                            }
                        });
        return builder.create();
    }

    public AlertDialog dialogoInstruccionesNivelMedio(){
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setTitle("NIVEL MEDIO")
                .setMessage("Se Presentaran 4 botones de colores, se da puntaje al presionar el boton "+
                        "que coindida con el color de la palabra.")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //no hacemos nada
                            }
                        })
                .setPositiveButton("JUGAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                utilidades.nivelJuego=2;
                                Intent intent=new Intent(actividad, Nivel2Activity.class);
                                startActivity(intent);
                                dismiss();
                            }
                        });
        return builder.create();
    }

    public AlertDialog dialogoInstruccionesLetraEscondida(){
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setTitle("Lentra Escondida")
                .setMessage("Busca entre un grupo de letras sólo la letra indicada")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //no hacemos nada
                            }
                        })
                .setPositiveButton("JUGAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                utilidades.nivelJuego=3;
                                Intent intent=new Intent(actividad, cantidadActivity.class);
                                startActivity(intent);
                                dismiss();
                            }
                        });
        return builder.create();
    }

    public AlertDialog dialogoInstruccionesMemoria(){
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setTitle("Lentra Escondida")
                .setMessage("Encuentra parejas de figuras impresas utilizando la memoria")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //no hacemos nada
                            }
                        })
                .setPositiveButton("JUGAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                utilidades.nivelJuego=4;
                                Intent intent=new Intent(actividad, Memorama.class);
                                startActivity(intent);
                                dismiss();
                            }
                        });
        return builder.create();
    }

    public AlertDialog dialogoInstruccionesRazonamiento(){
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setTitle("Lentra Escondida")
                .setMessage("Encuentra parejas de figuras impresas utilizando la memoria")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //no hacemos nada
                            }
                        })
                .setPositiveButton("JUGAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                utilidades.nivelJuego=5;
                                Intent intent=new Intent(actividad, RazonamientoActivity.class);
                                startActivity(intent);
                                dismiss();
                            }
                        });
        return builder.create();
    }

    public void asignarValoresPreferencias(){
        barraSuperior.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaFragments= (IComunicaFragments) this.actividad;
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