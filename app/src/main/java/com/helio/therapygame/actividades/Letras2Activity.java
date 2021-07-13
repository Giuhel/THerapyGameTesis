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

public class Letras2Activity extends AppCompatActivity {

    ImageButton imgj,imgk,imgl,imgm,imgn,imgñ,imgo,imgp,imgq;
    Button siguiente,inicio;

    MediaPlayer au_j,au_k,au_l,au_m,au_n,au_ñ,au_o,au_p,au_q;

    IComunicaFragments iComunicaFragments;
    Activity actividad;
    RelativeLayout layoutFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letras2);

        layoutFondo=findViewById(R.id.idLayoutFondoL2);
        inicio=findViewById(R.id.btnHomeL2);
        siguiente=findViewById(R.id.btnsiguienteL2);
        imgj=findViewById(R.id.imgJ);
        imgk=findViewById(R.id.imgK);
        imgl=findViewById(R.id.imgL);
        imgm=findViewById(R.id.imgM);
        imgn=findViewById(R.id.imgN);
        imgñ=findViewById(R.id.imgÑ);
        imgo=findViewById(R.id.imgO);
        imgp=findViewById(R.id.imgP);
        imgq=findViewById(R.id.imgQ);

        au_j= MediaPlayer.create(this,R.raw.j);
        au_k= MediaPlayer.create(this,R.raw.k);
        au_l= MediaPlayer.create(this,R.raw.l);
        au_m= MediaPlayer.create(this,R.raw.m);
        au_n= MediaPlayer.create(this,R.raw.n);
        au_ñ= MediaPlayer.create(this,R.raw.enie);
        au_o= MediaPlayer.create(this,R.raw.o);
        au_p= MediaPlayer.create(this,R.raw.p);
        au_q= MediaPlayer.create(this,R.raw.q);

        sonidos();
    }

    private void sonidos() {
        imgj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_j.start();
            }
        });
        imgk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_k.start();
            }
        });
        imgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_l.start();
            }
        });
        imgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_m.start();
            }
        });
        imgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_n.start();
            }
        });
        imgñ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_ñ.start();
            }
        });
        imgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_o.start();
            }
        });
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_p.start();
            }
        });
        imgq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_q.start();
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
        Intent intent=new Intent(this, Letras3Activity.class);
        startActivity(intent);
        finish();
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        siguiente.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        inicio.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }
}