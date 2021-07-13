package com.helio.therapygame.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Razonamiento4Activity extends AppCompatActivity {

    EditText N1M1,N2M1,ResuM1,N1M2,N2M2,ResuM2,N1M3,N2M3,ResuM3;
    Button btnResultado;

    int k;
    int c;
    int p;
    int resultado1,resultado2,resultado3;

    TextView kiwi,calabaza,platano;
    TextView intento;
    RelativeLayout fondo;

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
        setContentView(R.layout.activity_razonamiento4);

        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());

        fondo=findViewById(R.id.idLayoutFondoL);
        kiwi=findViewById(R.id.kiwi);
        calabaza=findViewById(R.id.calabaza);
        platano=findViewById(R.id.platano);

        N1M1=findViewById(R.id.txtnum1mul1);
        N2M1=findViewById(R.id.txtnum2mul1);
        ResuM1=findViewById(R.id.txtresultadomul1);
        N1M2=findViewById(R.id.txtnum1mul2);
        N2M2=findViewById(R.id.txtnum2mul2);
        ResuM2=findViewById(R.id.txtresultadomul2);
        N1M3=findViewById(R.id.txtnum1mul3);
        N2M3=findViewById(R.id.txtnum2mul3);
        ResuM3=findViewById(R.id.txtresultadomul3);
        intento=findViewById(R.id.intentos);
        btnResultado=findViewById(R.id.btnverificaR4);

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

    private void numerosRamdon(){
        Random r=new Random();
        k=r.nextInt(10);
        p=r.nextInt(10);
        c=r.nextInt(10);
        if(p==k||p==c || k==c){
            numerosRamdon();
        }else{
            kiwi.setText("= "+k);
            calabaza.setText("= "+c);
            platano.setText("= "+p);
        }
        resultado1=c*k;
        resultado2=k*p;
        resultado3=c*p;
    }

    private void verificaResultado() {
        if(!N1M1.getText().toString().trim().equals("") && !N2M1.getText().toString().trim().equals("")
                && !ResuM1.getText().toString().trim().equals("")
                && !N1M2.getText().toString().trim().equals("") && !N2M2.getText().toString().trim().equals("")
                && !ResuM2.getText().toString().trim().equals("")
                && !N1M3.getText().toString().trim().equals("") && !N2M3.getText().toString().trim().equals("")
                && !ResuM3.getText().toString().trim().equals("")){

            if(N1M1.getText().toString().equals(c+"")&&N2M1.getText().toString().equals(k+"")
                    &&ResuM1.getText().toString().equals(resultado1+"")
                    &&N1M2.getText().toString().equals(k+"")&&N2M2.getText().toString().equals(p+"")
                    &&ResuM2.getText().toString().equals(resultado2+"") &&
                    N1M3.getText().toString().equals(c+"")&&N2M3.getText().toString().equals(p+"")
                    &&ResuM3.getText().toString().equals(resultado3+"")){

                utilidades.puntaje=utilidades.puntaje+15;
                utilidades.correctas=utilidades.correctas+9;
                btnResultado.setEnabled(false);
                ison=false;
                mili=0;
                seg=0;
                minutos=0;
                Toast.makeText(this,"Excelente M1",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, ResultadoJuegoActivity.class);
                startActivity(intent);
                finish();
            }else{
                intentos--;
                utilidades.incorrectas=utilidades.incorrectas+3;
                asignarvalores();
                Toast.makeText(this,"te equivocaste M1:c",Toast.LENGTH_SHORT).show();
            }
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