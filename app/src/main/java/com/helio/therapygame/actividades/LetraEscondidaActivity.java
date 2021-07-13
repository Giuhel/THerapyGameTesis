package com.helio.therapygame.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.utilidades;

public class LetraEscondidaActivity extends AppCompatActivity {

    ToggleButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20;
    Button btnProcesar;
    boolean marcado=false;
    int puntaje;
    RelativeLayout fondobarra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letra_escondida);

        fondobarra=findViewById(R.id.idLayoutFondoL);
        btn1=findViewById(R.id.tgbtn1);
        btn2=findViewById(R.id.tgbtn2);
        btn3=findViewById(R.id.tgbtn3);
        btn4=findViewById(R.id.tgbtn4);
        btn5=findViewById(R.id.tgbtn5);
        btn6=findViewById(R.id.tgbtn6);
        btn7=findViewById(R.id.tgbtn7);
        btn8=findViewById(R.id.tgbtn8);
        btn9=findViewById(R.id.tgbtn9);
        btn10=findViewById(R.id.tgbtn10);
        btn11=findViewById(R.id.tgbtn11);
        btn12=findViewById(R.id.tgbtn12);
        btn13=findViewById(R.id.tgbtn13);
        btn14=findViewById(R.id.tgbtn14);
        btn15=findViewById(R.id.tgbtn15);
        btn16=findViewById(R.id.tgbtn16);
        btn17=findViewById(R.id.tgbtn17);
        btn18=findViewById(R.id.tgbtn18);
        btn19=findViewById(R.id.tgbtn19);
        btn20=findViewById(R.id.tgbtn20);

        btnProcesar=findViewById(R.id.btnProcesar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn1.isChecked()){
                    btn1.setBackgroundColor(getResources().getColor(R.color.colorGris));
                    marcado=true;
                }else{
                    btn1.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                    || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                    || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn2.isChecked()){
                    btn2.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn2.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn3.isChecked()){
                    btn3.setBackgroundColor(getResources().getColor(R.color.colorGris));
                    marcado=true;
                }else{
                    btn3.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn1.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn4.isChecked()){
                    btn4.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn4.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn5.isChecked()){
                    marcado=true;
                    btn5.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn5.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn1.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn6.isChecked()){
                    marcado=true;
                    btn6.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn6.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn1.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn7.isChecked()){
                    btn7.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn7.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn8.isChecked()){
                    marcado=true;
                    btn8.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn8.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn1.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn9.isChecked()){
                    marcado=true;
                    btn9.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn9.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn1.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn10.isChecked()){
                    marcado=true;
                    btn10.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn10.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn1.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn11.isChecked()){
                    marcado=true;
                    btn11.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn11.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn1.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn12.isChecked()){
                    marcado=true;
                    btn12.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn12.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn1.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn13.isChecked()){
                    marcado=true;
                    btn13.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn13.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn1.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn14.isChecked()){
                    btn14.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn14.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                }
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn15.isChecked()){
                    marcado=true;
                    btn15.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn15.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn1.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn16.isChecked()){
                    marcado=true;
                    btn16.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn16.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn1.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn17.isChecked()){
                    marcado=true;
                    btn17.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn17.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn1.isChecked() || btn18.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn18.isChecked()){
                    marcado=true;
                    btn18.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn18.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn1.isChecked() ||btn20.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn19.isChecked()){
                    btn19.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn19.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                }
            }
        });

        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn20.isChecked()){
                    marcado=true;
                    btn20.setBackgroundColor(getResources().getColor(R.color.colorGris));
                }else{
                    btn20.setBackgroundColor(getResources().getColor(R.color.colorBlanco));
                    if(btn3.isChecked() || btn5.isChecked() || btn6.isChecked() || btn8.isChecked() ||btn9.isChecked() || btn10.isChecked()
                            || btn11.isChecked() || btn12.isChecked() || btn13.isChecked() ||btn15.isChecked() || btn16.isChecked()
                            || btn17.isChecked() || btn18.isChecked() ||btn1.isChecked()){
                        marcado=true;
                    }else{
                        marcado=false;
                    }
                }
            }
        });

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLetra();
            }
        });
    }

    private void validarLetra() {
        if(btn2.isChecked()&&btn4.isChecked()&&btn7.isChecked()&&btn14.isChecked()&&btn19.isChecked()&&marcado==false){
            Toast.makeText(this,"Excelente",Toast.LENGTH_SHORT).show();
            utilidades.puntaje=10;
            utilidades.correctas=5;
            utilidades.incorrectas=0;
            Intent intent=new Intent(this, ResultadoJuegoActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Mala Suerte",Toast.LENGTH_SHORT).show();
            utilidades.puntaje=1;
        }
    }

    public void terminar(){
        this.finish();
    }

    public void asignarValoresPreferencias(){
        fondobarra.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
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