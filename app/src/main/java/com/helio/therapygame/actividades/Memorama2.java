package com.helio.therapygame.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.utilidades;

import java.util.ArrayList;
import java.util.Collections;

public class Memorama2 extends Activity {

    ImageButton imb00,imb01,imb02,imb03,imb04,imb05,imb06,imb07,imb08,imb09,imb10,imb11;
    ImageButton[] tablero=new ImageButton[12];
    TextView puntaje,correctas,incorrectas;
    int [] imagenes;

    ArrayList<Integer> desordenado;
    ImageButton primero;
    int numprimero,numsegundo;
    int fondo;
    boolean bloqueo=false;
    final Handler handler=new Handler();


    RelativeLayout fondolayout;
    TableLayout fondotableta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorama2);

        init();

    }

    private void cargarTablero(){
        fondolayout=findViewById(R.id.idLayoutFondoM);
        fondotableta=findViewById(R.id.fondotable);
        imb00=findViewById(R.id.btn00);
        imb01=findViewById(R.id.btn01);
        imb02=findViewById(R.id.btn02);
        imb03=findViewById(R.id.btn03);
        imb04=findViewById(R.id.btn04);
        imb05=findViewById(R.id.btn05);
        imb06=findViewById(R.id.btn06);
        imb07=findViewById(R.id.btn07);
        imb08=findViewById(R.id.btn08);
        imb09=findViewById(R.id.btn09);
        imb10=findViewById(R.id.btn10);
        imb11=findViewById(R.id.btn11);

        tablero[0]=imb00;
        tablero[1]=imb01;
        tablero[2]=imb02;
        tablero[3]=imb03;
        tablero[4]=imb04;
        tablero[5]=imb05;
        tablero[6]=imb06;
        tablero[7]=imb07;
        tablero[8]=imb08;
        tablero[9]=imb09;
        tablero[10]=imb10;
        tablero[11]=imb11;
    }

    private void cargarTextView(){
        puntaje=findViewById(R.id.txtpuntajeM);
        correctas=findViewById(R.id.txtCorrectasM);
        incorrectas=findViewById(R.id.txtInCorrectasM);


        puntaje.setText(utilidades.puntaje+"");
        correctas.setText(utilidades.correctas+"");
        incorrectas.setText( utilidades.incorrectas+"");

    }

    private void cargarImagenes(){
        imagenes=new int[]{
                R.drawable.manzana,
                R.drawable.uva,
                R.drawable.pera,
                R.drawable.platanos,
                R.drawable.naranja,
                R.drawable.sandia,
        };

        fondo=R.drawable.fondocarta;

    }

    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result=new ArrayList<Integer>();
        for(int i=0;i<longitud*2;i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        //System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void comprobar(int i,final ImageButton imgb){
        if(primero==null){
            primero=imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[desordenado.get(i)]);
            primero.setEnabled(false);
            numprimero=desordenado.get(i);
        }else{
            bloqueo=true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[desordenado.get(i)]);
            imgb.setEnabled(false);
            numsegundo=desordenado.get(i);
            if(numprimero==numsegundo){
                primero = null;
                bloqueo=false;
                utilidades.puntaje=utilidades.puntaje+2;
                utilidades.correctas++;
                puntaje.setText(utilidades.puntaje+"");
                correctas.setText(utilidades.correctas+"");
                if(utilidades.puntaje==20){
                    Toast.makeText(getApplicationContext(),"Ganaste",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this, Memorama3.class);
                    startActivity(intent);
                    finish();
                }
            }else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo=false;
                        primero=null;
                        utilidades.incorrectas++;
                        incorrectas.setText(utilidades.incorrectas+"");
                    }
                },1000);
            }
        }
    }

    private void init(){
        cargarTablero();
        cargarTextView();
        cargarImagenes();
        desordenado=barajar(imagenes.length);
        for(int i=0;i<tablero.length;i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            //tablero[i].setBackgroundColor(getResources().getColor(R.color.colorBlanco));
            tablero[i].setImageResource(imagenes[desordenado.get(i)]);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<tablero.length;i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 3000);
        for(int i=0;i<tablero.length;i++){
            final int j=i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo){
                        comprobar(j, tablero[j]);
                    }
                }
            });
        }
    }

    public void asignarValoresPreferencias(){
        fondolayout.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        fondotableta.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
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
