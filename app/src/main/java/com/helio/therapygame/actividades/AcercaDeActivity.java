package com.helio.therapygame.actividades;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class AcercaDeActivity extends AppCompatActivity {

    FloatingActionButton btnInicio;
    RelativeLayout layoutFondo;
    ImageButton icoYoutube,icoFace,icoInstagram,icoTwitter,icoTiktok;
    TextView nombre;
    ImageView avatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        layoutFondo=findViewById(R.id.idLayoutFondo);
        icoYoutube=findViewById(R.id.icoYoutube);
        icoFace=findViewById(R.id.icoFace);
        icoInstagram=findViewById(R.id.icoInstagram);
        icoTwitter=findViewById(R.id.icoTwitter);
        icoTiktok=findViewById(R.id.icoTiktok);
        nombre=findViewById(R.id.textNickName);
        avatar=findViewById(R.id.avatarImage);

        cargardatos();

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layoutFondo.setBackground(getResources().getDrawable(PreferenciasJuego.formaBanner));
        }else{
            layoutFondo.setBackgroundDrawable(getResources().getDrawable(PreferenciasJuego.formaBanner));
        }*/

        Drawable shape = (Drawable) layoutFondo.getBackground();
        shape.setColorFilter(getResources().getColor(PreferenciasJuego.colorTema), android.graphics.PorterDuff.Mode.SRC);

        btnInicio=findViewById(R.id.btnHome);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void onClick(View view) {
        Intent miIntent=null;
        boolean error=false;
        switch (view.getId()){
            case R.id.icoYoutube: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCMN72RNgnI62hXA9YZLtOhQ?view_as=subscriber"));
                break;
            case R.id.icoFace: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/helio.pizarro/"));
                break;
            case R.id.icoTwitter: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/giuhel2018"));
                break;
            case R.id.icoInstagram: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/helio_pizarro/"));
                break;
            case R.id.icoTiktok: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/@giuhel_pip?lang=es"));
                break;
            case R.id.btnDescargar:
                String url="https://drive.google.com/file/d/1wDBGYi8ccbZt7GWYqUfF9LJ4U_Wxcbkv/view?usp=sharing";

                if (!url.equals("vacio")){
                    Uri uri = Uri.parse(url);
                    miIntent = new Intent(Intent.ACTION_VIEW,uri);
                }else{
                    error=true;
                    Toast.makeText(this, "No se puede obtener el manual de usuario, intente mas tarde", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        if (error==false){
            startActivity(miIntent);
        }
    }

    private void cargardatos(){
        if(PreferenciasJuego.avatarId==1){
            avatar.setImageResource(R.drawable.cara_simio_banner);
        }else {
            avatar.setImageResource(utilidades.listaAvatars.get(PreferenciasJuego.avatarId-1).getAvatarId());
        }
        nombre.setText(PreferenciasJuego.nickName);
    }

    @Override
    public void onBackPressed() {

    }
}