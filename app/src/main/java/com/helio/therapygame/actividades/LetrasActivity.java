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

public class LetrasActivity extends AppCompatActivity {

    ImageButton imga,imgb,imgc,imgd,imge,imgf,imgg,imgh,imgi;
    Button siguiente,inicio;

    MediaPlayer au_a,au_b,au_c,au_d,au_e,au_f,au_g,au_h,au_i;

    RelativeLayout layoutFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letras);

        layoutFondo=findViewById(R.id.idLayoutFondoL);
        inicio=findViewById(R.id.btnHomeL);
        siguiente=findViewById(R.id.btnsiguienteL);
        imga=findViewById(R.id.imgA);
        imgb=findViewById(R.id.imgB);
        imgc=findViewById(R.id.imgC);
        imgd=findViewById(R.id.imgD);
        imge=findViewById(R.id.imgE);
        imgf=findViewById(R.id.imgF);
        imgg=findViewById(R.id.imgG);
        imgh=findViewById(R.id.imgH);
        imgi=findViewById(R.id.imgI);

        au_a= MediaPlayer.create(this,R.raw.a);
        au_b= MediaPlayer.create(this,R.raw.be);
        au_c= MediaPlayer.create(this,R.raw.c);
        au_d= MediaPlayer.create(this,R.raw.d);
        au_e= MediaPlayer.create(this,R.raw.e);
        au_f= MediaPlayer.create(this,R.raw.f);
        au_g= MediaPlayer.create(this,R.raw.g);
        au_h= MediaPlayer.create(this,R.raw.h);
        au_i= MediaPlayer.create(this,R.raw.i);

        sonidos();
    }

    private void sonidos() {
        imga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_a.start();
            }
        });
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_b.start();
            }
        });
        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_c.start();
            }
        });
        imgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_d.start();
            }
        });
        imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_e.start();
            }
        });
        imgf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_f.start();
            }
        });
        imgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_g.start();
            }
        });
        imgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_h.start();
            }
        });
        imgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_i.start();
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

    private void Siguiente(){
        Intent intent=new Intent(this, Letras2Activity.class);
        startActivity(intent);
        finish();
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        siguiente.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        inicio.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }

    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }

    @Override
    public void onBackPressed() {

    }
}