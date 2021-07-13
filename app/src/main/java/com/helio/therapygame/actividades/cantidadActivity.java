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

public class cantidadActivity extends AppCompatActivity {

    EditText num;
    Button verifica;
    ImageView oso;
    int randon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad);

        num=findViewById(R.id.numoso);
        verifica=findViewById(R.id.verificar);
        oso=findViewById(R.id.oso);

        utilidades.puntaje=0;
        utilidades.correctas=0;
        utilidades.incorrectas=0;

        imagenRandon();

        verifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarimagen();
            }
        });
    }

    private void verificarimagen() {
        if(randon==1){
            if(num.getText().toString().equals("3")){
                utilidades.puntaje=utilidades.puntaje+5;
                utilidades.correctas=utilidades.correctas+1;
                Intent intent=new Intent(this, cantidad2Activity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }else if(randon==2 || randon==4){
            if(num.getText().toString().equals("2")){
                utilidades.puntaje=utilidades.puntaje+5;
                utilidades.correctas=utilidades.correctas+1;
                Intent intent=new Intent(this, cantidad2Activity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }else if(randon==3){
            if(num.getText().toString().equals("1")){
                utilidades.puntaje=utilidades.puntaje+5;
                utilidades.correctas=utilidades.correctas+1;
                Intent intent=new Intent(this, cantidad2Activity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }else{
            if(num.getText().toString().equals("1")){
                utilidades.puntaje=utilidades.puntaje+5;
                utilidades.correctas=utilidades.correctas+1;
                Intent intent=new Intent(this, cantidad2Activity.class);
                startActivity(intent);
                finish();
            }else{
                utilidades.incorrectas++;
            }
        }
    }

    private void imagenRandon() {
        Random r=new Random();
        randon=r.nextInt(5);
        if(randon==1){
            oso.setBackgroundResource(getResources().getIdentifier("oso","drawable",getPackageName()));
        }else if(randon==2){
            oso.setBackgroundResource(getResources().getIdentifier("vaca","drawable",getPackageName()));
        }else if(randon==3){
            oso.setBackgroundResource(getResources().getIdentifier("ardilla","drawable",getPackageName()));
        }else if(randon==4){
            oso.setBackgroundResource(getResources().getIdentifier("cerdo","drawable",getPackageName()));
        }else{
            oso.setBackgroundResource(getResources().getIdentifier("jirafa","drawable",getPackageName()));
        }

    }
}