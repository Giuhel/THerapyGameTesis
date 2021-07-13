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

public class Razonamiento3Activity extends AppCompatActivity {

    EditText N1R1,N2R1,ResuR1,N1R2,N2R2,ResuR2;
    Button btnResultado;

    int p;
    int m;
    int u;
    int c;
    int resultado1,resultado2;

    TextView piña,mazana,uva,cereza;
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
        setContentView(R.layout.activity_razonamiento3);

        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());

        fondo=findViewById(R.id.idLayoutFondoL);
        piña=findViewById(R.id.pina);
        mazana=findViewById(R.id.manzana);
        uva=findViewById(R.id.uva);
        cereza=findViewById(R.id.cereza);
        N1R1=findViewById(R.id.txtnum1res1);
        N2R1=findViewById(R.id.txtnum2res1);
        ResuR1=findViewById(R.id.txtresultadoRes1);
        N1R2=findViewById(R.id.txtnum1res2);
        N2R2=findViewById(R.id.txtnum2res2);
        ResuR2=findViewById(R.id.txtresultadoRes2);
        intento=findViewById(R.id.intentos);
        btnResultado=findViewById(R.id.btnverificaR3);

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
        p=r.nextInt(10);
        m=r.nextInt(10);
        u=r.nextInt(10);
        c=r.nextInt(10);
        if(p==m||m==u || u==c || p==u || p==c ||m==c){
            numerosRamdon();
        }else{
            piña.setText("= "+p);
            mazana.setText("= "+m);
            uva.setText("= "+u);
            cereza.setText("= "+c);
        }

        resultado1=u-p;
        resultado2=m-c;

        if(resultado1<=0 || resultado2<=0){
            numerosRamdon();
        }
    }

    private void verificaResultado() {
        if(!N1R1.getText().toString().trim().equals("") && !N2R1.getText().toString().trim().equals("")
                && !ResuR1.getText().toString().trim().equals("")
                && !N1R2.getText().toString().trim().equals("") && !N2R2.getText().toString().trim().equals("")
                && !ResuR2.getText().toString().trim().equals("")){

            if(N1R1.getText().toString().equals(u+"")&&N2R1.getText().toString().equals(p+"")
                    &&ResuR1.getText().toString().equals(resultado1+"")
                    &&N1R2.getText().toString().equals(m+"")&&N2R2.getText().toString().equals(c+"")
                    &&ResuR2.getText().toString().equals(resultado2+"")){

                utilidades.puntaje=utilidades.puntaje+10;
                utilidades.correctas=utilidades.correctas+6;
                //utilidades.intentos=intentos;
                btnResultado.setEnabled(false);
                ison=false;
                mili=0;
                seg=0;
                minutos=0;
                Toast.makeText(this,"Excelente",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, Razonamiento4Activity.class);
                startActivity(intent);
                finish();
            }else{
                intentos--;
                utilidades.incorrectas=utilidades.incorrectas+2;
                asignarvalores();
                Toast.makeText(this,"te equivocaste",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Falta Ingresar Datos",Toast.LENGTH_SHORT).show();
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