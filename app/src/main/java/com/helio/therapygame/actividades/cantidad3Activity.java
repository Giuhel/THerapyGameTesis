package com.helio.therapygame.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.utilidades;

import java.util.Random;

public class cantidad3Activity extends AppCompatActivity {

    EditText num1,num2,num3;
    Button verifica;
    ImageView image1,image2,image3;
    int randon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad3);

        num1=findViewById(R.id.numimagen1);
        num2=findViewById(R.id.numimagen2);
        num3=findViewById(R.id.numimagen3);
        verifica=findViewById(R.id.verificar3);
        image1=findViewById(R.id.imagen1);
        image2=findViewById(R.id.imagen2);
        image3=findViewById(R.id.imagen3);

        imagenesRandom();

        verifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificar();
            }
        });
    }

    private void verificar() {
        if(randon==1){
            if(num1.getText().toString().equals("2") && num2.getText().toString().equals("1") && num3.getText().toString().equals("3")){
                utilidades.puntaje=utilidades.puntaje+10;
                utilidades.correctas=utilidades.correctas+3;
                Intent intent=new Intent(this, ResultadoJuegoActivity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }else{
            if(num1.getText().toString().equals("2") && num2.getText().toString().equals("1") && num3.getText().toString().equals("2")){
                utilidades.puntaje=utilidades.puntaje+10;
                utilidades.correctas=utilidades.correctas+3;
                Intent intent=new Intent(this, ResultadoJuegoActivity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }
    }

    private void imagenesRandom() {
        Random r=new Random();
        randon=r.nextInt(2);

        if(randon==1){
            image1.setBackgroundResource(getResources().getIdentifier("dino1","drawable",getPackageName()));
            image2.setBackgroundResource(getResources().getIdentifier("dino2","drawable",getPackageName()));
            image3.setBackgroundResource(getResources().getIdentifier("dino5","drawable",getPackageName()));
        }else{
            image1.setBackgroundResource(getResources().getIdentifier("dino4","drawable",getPackageName()));
            image2.setBackgroundResource(getResources().getIdentifier("dino3","drawable",getPackageName()));
            image3.setBackgroundResource(getResources().getIdentifier("dino1","drawable",getPackageName()));
        }
    }
}