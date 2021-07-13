package com.helio.therapygame.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.interfaces.IComunicaFragments;

public class ColoresActivity extends AppCompatActivity {

    ImageButton rojo,narajna,verde,amarillo,azul;
    Button siguiente,inicio;

    private MediaPlayer au_rojo,au_naranja,au_verde,au_amarillo,au_azul;

    IComunicaFragments iComunicaFragments;
    Activity actividad;
    RelativeLayout layoutFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        au_rojo= MediaPlayer.create(this,R.raw.rojo);
        au_amarillo= MediaPlayer.create(this,R.raw.amarillo);
        au_azul= MediaPlayer.create(this,R.raw.azul);
        au_naranja= MediaPlayer.create(this,R.raw.anaranjado);
        au_verde= MediaPlayer.create(this,R.raw.verde);

        rojo=findViewById(R.id.imgrojo);
        amarillo=findViewById(R.id.imgamarillo);
        azul=findViewById(R.id.imgazul);
        narajna=findViewById(R.id.imgnaranja);
        verde=findViewById(R.id.imgverde);
        siguiente=findViewById(R.id.btnsiguiente);
        inicio=findViewById(R.id.btnHomee);
        layoutFondo=findViewById(R.id.idLayoutFondoC);

        sonidos();
    }

    private void sonidos(){
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_rojo.start();
            }
        });

        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_amarillo.start();
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_azul.start();
            }
        });

        narajna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_naranja.start();
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_verde.start();
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Siguiente();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }

    @Override
    public void onBackPressed() {

    }

    private void Siguiente(){
        Intent intent=new Intent(this, LetrasActivity.class);
        startActivity(intent);
        finish();
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        siguiente.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        inicio.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }
}