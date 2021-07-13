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

public class cantidad2Activity extends AppCompatActivity {

    EditText num1,num2;
    Button verifica;
    ImageView image1,image2;
    int randon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad2);

        num1=findViewById(R.id.numimagen1);
        num2=findViewById(R.id.numimagen2);
        verifica=findViewById(R.id.verificar2);
        image1=findViewById(R.id.imagen1);
        image2=findViewById(R.id.imagen2);

        imagenRandon();

        verifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificar();
            }
        });
    }

    private void verificar() {
        if(randon==1 || randon==4){
            if(num1.getText().toString().equals("3") && num2.getText().toString().equals("2")){
                utilidades.puntaje=utilidades.puntaje+5;
                utilidades.correctas=utilidades.correctas+2;
                Intent intent=new Intent(this, cantidad3Activity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }else {
            if(num1.getText().toString().equals("2") && num2.getText().toString().equals("1")){
                utilidades.puntaje=utilidades.puntaje+5;
                utilidades.correctas=utilidades.correctas+2;
                Intent intent=new Intent(this, cantidad3Activity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }
    }

    private void imagenRandon() {
        Random r=new Random();
        randon=r.nextInt(4);

        if(randon==1 || randon==4){
            image1.setBackgroundResource(getResources().getIdentifier("panda1","drawable",getPackageName()));
            image2.setBackgroundResource(getResources().getIdentifier("panda3","drawable",getPackageName()));
        }else{
            image1.setBackgroundResource(getResources().getIdentifier("panda2","drawable",getPackageName()));
            image2.setBackgroundResource(getResources().getIdentifier("panda5","drawable",getPackageName()));
        }
    }
}