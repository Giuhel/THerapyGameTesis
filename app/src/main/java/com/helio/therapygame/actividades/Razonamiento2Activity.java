package com.helio.therapygame.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.utilidades;

import java.util.Random;

public class Razonamiento2Activity extends AppCompatActivity {

    EditText N1S1,N2S1,N3S1,ResuS1,N1S2,N2S2,N3S2,ResuS2;
    Button btnResultado;

    int p;
    int f;
    int fb;
    int resultado1,resultado2;
    boolean Op1 = false;

    TextView pera,fresa,frambuesa;
    RelativeLayout fondo;

    TextView intento;
    int intentos;
    String modo;   //modo de juego
    long tiempo;   //tiempo de juego
    int finalizaJuego=0;

    boolean ison=true;
    Thread Cronos;
    int mili=0,seg=0,minutos=0;
    Handler h=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razonamiento2);

        fondo=findViewById(R.id.idLayoutFondoL);
        pera=findViewById(R.id.pera);
        fresa=findViewById(R.id.fresa);
        frambuesa=findViewById(R.id.frambuesa);
        N1S1=findViewById(R.id.txtnum1sum1);
        N2S1=findViewById(R.id.txtnum2sum1);
        N3S1=findViewById(R.id.txtnum3sum1);
        ResuS1=findViewById(R.id.txtresultadoSum1);
        N1S2=findViewById(R.id.txtnum1sum2);
        N2S2=findViewById(R.id.txtnum2sum2);
        N3S2=findViewById(R.id.txtnum3sum2);
        ResuS2=findViewById(R.id.txtresultadoSum2);
        intento=findViewById(R.id.intentos);
        btnResultado=findViewById(R.id.btnverificaR2);

        //intento.setText("te quedan "+intentos+" intentos");
        numerosRamdon();
        inicializarValore();
        asignarvalores();
        if(modo.equals("TIEMPO")){
            conometro();
        }

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaResultado();
            }
        });
    }

    private void numerosRamdon() {
        Random r=new Random();
        p=r.nextInt(10);
        f=r.nextInt(10);
        fb=r.nextInt(10);
        if(p==f||f==fb || fb==p){
            numerosRamdon();
        }else{
            pera.setText("= "+p);
            fresa.setText("= "+f);
            frambuesa.setText("= "+fb);
        }

        resultado1=p+f+fb;
        resultado2=p+fb+p;
    }

    private void verificaResultado() {
        if(!N1S1.getText().toString().trim().equals("") && !N2S1.getText().toString().trim().equals("") &&
                !N3S1.getText().toString().trim().equals("") && !ResuS1.getText().toString().trim().equals("") &&
                !N1S2.getText().toString().trim().equals("") && !N2S2.getText().toString().trim().equals("") &&
                !N3S2.getText().toString().trim().equals("") && !ResuS2.getText().toString().trim().equals("")){

            if(N1S1.getText().toString().equals(fb+"")&&N2S1.getText().toString().equals(f+"")&&N3S1.getText().toString().equals(p+"")
                    &&ResuS1.getText().toString().equals(resultado1+"")
                    &&N1S2.getText().toString().equals(p+"")&&N2S2.getText().toString().equals(fb+"")&&N3S2.getText().toString().equals(p+"")
                    &&ResuS2.getText().toString().equals(resultado2+"")){

                utilidades.puntaje=utilidades.puntaje+10;
                utilidades.correctas=utilidades.correctas+8;
                //utilidades.intentos=intentos;
                btnResultado.setEnabled(false);
                ison=false;
                mili=0;
                seg=0;
                minutos=0;
                Toast.makeText(this,"Excelente ",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, Razonamiento3Activity.class);
                startActivity(intent);
                finish();
            }else{
                intentos--;
                asignarvalores();
                utilidades.incorrectas=utilidades.incorrectas+2;
                Toast.makeText(this,"te equivocaste ",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"te falta ingresar datos ",Toast.LENGTH_SHORT).show();
        }
        asignarvalores();
        terminarJuego();
    }


    private void asignarvalores() {
        if (modo.equals("INTENTOS")){
            intento.setText("te quedan "+intentos+" intentos");
        }else {
            intento.setText("te quedan "+tiempo+" intentos");
        }
    }

    private void inicializarValore(){
        modo= PreferenciasJuego.modoJuego;

        if(modo.equals("INTENTOS")){
            intentos=PreferenciasJuego.numIntentos;
        }else{
            intentos=0;
            tiempo=PreferenciasJuego.tiempojuego;
            if(tiempo==60000){
                tiempo=tiempo/tiempo;
            }else if(tiempo==120000){
                tiempo=tiempo/60000;
            }else if(tiempo==180000){
                tiempo=tiempo/60000;
            }
        }
    }

    private void terminarJuego() {
        if(finalizaJuego==0 && (modo.equals("INTENTOS") && intentos==0) || (modo.equals("TIEMPO") && minutos==tiempo)){
            finalizaJuego=1;
            cerrarteclado();
            btnResultado.setEnabled(false);
            ison=false;
            mili=0;
            seg=0;
            minutos=0;
            Intent miIntent=new Intent(this, ResultadoJuegoActivity.class);
            startActivity(miIntent);
            finish();
        }
    }

    private void conometro() {
        Cronos=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(ison){
                        try {
                            Thread.sleep(1);
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mili++;
                        if(mili==999){
                            seg++;
                            mili=0;
                        }
                        if(seg==59){
                            minutos++;
                            seg=0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m="",s="",mi="";
                                if(mili<10){
                                    m="00"+mili;
                                }else if(mili<100){
                                    m="0"+mili;
                                }else{
                                    m=""+mili;
                                }
                                if(seg<10){
                                    s="0"+seg;
                                }else{
                                    s=""+seg;
                                }
                                if(minutos<10){
                                    mi="0"+minutos;
                                }else {
                                    mi=""+minutos;
                                }
                                //intento.setText(mi+":"+s+":"+m);
                                intento.setText("tienes "+tiempo+" minutos - "+mi+":"+s+":"+m);
                                terminarJuego();
                            }
                        });
                    }
                }
            }
        });
        Cronos.start();
    }

    private void cerrarteclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void asignarValoresPreferencias(){
        fondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        asignarValoresPreferencias();
    }

    @Override
    public void onBackPressed() {

    }
}