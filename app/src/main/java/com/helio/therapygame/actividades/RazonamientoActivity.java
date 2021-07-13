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

public class RazonamientoActivity extends AppCompatActivity {

    EditText N1S1,N2S1,N3S1,ResuS1;
    Button btnResultado;

    int n;
    int l;
    int resultado;
    TextView naranja,limon;

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
        setContentView(R.layout.activity_razonamiento);

        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());

        naranja=findViewById(R.id.naranja);
        limon=findViewById(R.id.limon);
        N1S1=findViewById(R.id.txtnum1sum1);
        N2S1=findViewById(R.id.txtnum2sum1);
        N3S1=findViewById(R.id.txtnum3sum1);
        ResuS1=findViewById(R.id.txtresultadoSum1);
        intento=findViewById(R.id.intentos);
        fondo=findViewById(R.id.idLayoutFondoL);
        btnResultado=findViewById(R.id.btnverifica);
        numerosRandom();
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



    private void numerosRandom(){
        Random r=new Random();
        n=r.nextInt(6);
        l=r.nextInt(6);
        if(n==l||n==1 || l ==1){
            numerosRandom();
        }else{
            limon.setText("= "+l);
            naranja.setText("= "+n);
        }
        resultado=n+l+n;
    }

    private void verificaResultado() {
        if(!N1S1.getText().toString().trim().equals("") && !N2S1.getText().toString().trim().equals("") &&
                !N3S1.getText().toString().trim().equals("") && !ResuS1.getText().toString().trim().equals("")){

            if(N1S1.getText().toString().equals(n+"")&&N2S1.getText().toString().equals(l+"")&&N3S1.getText().toString().equals(n+"")
                    &&ResuS1.getText().toString().equals(resultado+"")){
                utilidades.puntaje=10;
                utilidades.correctas=4;
                btnResultado.setEnabled(false);
                //utilidades.intentos=intentos;
                ison=false;
                mili=0;
                seg=0;
                minutos=0;
                Toast.makeText(this,"Excelente",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, Razonamiento2Activity.class);
                startActivity(intent);
                finish();
            }else{
                intentos--;
                utilidades.puntaje=0;
                utilidades.incorrectas=utilidades.incorrectas+2;
                asignarvalores();
                Toast.makeText(this,"te equivocaste menos un intento",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Te Falta Ingresar Datos",Toast.LENGTH_SHORT).show();
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
        utilidades.correctas=0;
        utilidades.incorrectas=0;
        utilidades.puntaje=0;

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

    /*private void iniciarCuenta(){
        int minu= (int) tiempo;
        int segu= (int) tiempo/1000;
        long valor= minu+segu;

        CountDownTimer cuenta= new CountDownTimer(valor,1000) {
            @Override
            public void onTick(long lo) {
                long tiempo=lo/1000;
                int minutos = (int) (tiempo/60);
                segundos = tiempo % 60;
                String minutos_mostrar=String.format("%02d",minutos);
                String segundos_mostrar=String.format("%02d",segundos);
                intento.setText(""+minutos_mostrar+" : "+segundos_mostrar);
            }

            @Override
            public void onFinish() {
                Toast.makeText(RazonamientoActivity.this,"termino",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RazonamientoActivity.this, ResultadoJuegoActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }*/


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