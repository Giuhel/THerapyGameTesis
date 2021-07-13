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

public class Letras3Activity extends AppCompatActivity {

    ImageButton imgr,imgs,imgt,imgu,imgv,imgw,imgx,imgy,imgz;
    Button inicio;

    MediaPlayer au_r,au_s,au_t,au_u,au_v,au_w,au_x,au_y,au_z;

    IComunicaFragments iComunicaFragments;
    Activity actividad;
    RelativeLayout layoutFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letras3);

        layoutFondo=findViewById(R.id.idLayoutFondoL3);
        inicio=findViewById(R.id.btnHomeL3);
        imgr=findViewById(R.id.imgR);
        imgs=findViewById(R.id.imgS);
        imgt=findViewById(R.id.imgT);
        imgu=findViewById(R.id.imgU);
        imgv=findViewById(R.id.imgV);
        imgw=findViewById(R.id.imgW);
        imgx=findViewById(R.id.imgX);
        imgy=findViewById(R.id.imgY);
        imgz=findViewById(R.id.imgZ);

        au_r= MediaPlayer.create(this,R.raw.r);
        au_s= MediaPlayer.create(this,R.raw.s);
        au_t= MediaPlayer.create(this,R.raw.t);
        au_u= MediaPlayer.create(this,R.raw.u);
        au_v= MediaPlayer.create(this,R.raw.v);
        au_w= MediaPlayer.create(this,R.raw.w);
        au_x= MediaPlayer.create(this,R.raw.x);
        au_y= MediaPlayer.create(this,R.raw.y);
        au_z= MediaPlayer.create(this,R.raw.z);

        sonidos();

    }

    private void sonidos() {
        imgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_r.start();
            }
        });

        imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_s.start();
            }
        });
        imgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_t.start();
            }
        });
        imgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_u.start();
            }
        });
        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_v.start();
            }
        });
        imgw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_w.start();
            }
        });
        imgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_x.start();
            }
        });
        imgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_y.start();
            }
        });
        imgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                au_z.start();
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
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