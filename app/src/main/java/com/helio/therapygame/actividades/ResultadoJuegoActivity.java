package com.helio.therapygame.actividades;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.helio.therapygame.R;
import com.helio.therapygame.clases.ConexionSQLiteHelper;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.VolleySingleton;
import com.helio.therapygame.clases.utilidades;
import com.helio.therapygame.interfaces.IComunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class ResultadoJuegoActivity extends AppCompatActivity {

    TextView txtResCorrectas,txtResIncorrectas,txtCorrectas,txtIncorrectas, txtResultados,txtPuntaje,txtResPuntaje;
    FloatingActionButton btnInicio;
    TextView textNickName;
    ImageView imageAvatar;
    RelativeLayout layoutFondo;
    IComunicaFragments iComunicaFragments;

    //webservice
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_juego);

        txtResultados=findViewById(R.id.txtResultados);
        txtCorrectas=findViewById(R.id.txtPalabrasCorrectas);
        txtIncorrectas=findViewById(R.id.txtPalabrasIncorrectas);
        txtResCorrectas=findViewById(R.id.txtResPalabrasCorrectas);
        txtResIncorrectas=findViewById(R.id.txtResPalabrasIncorrectas);
        txtPuntaje=findViewById(R.id.txtPuntajeTitulo);
        txtResPuntaje=findViewById(R.id.txtPuntaje);

        textNickName=findViewById(R.id.textNicname);
        imageAvatar=findViewById(R.id.avatarImage);

        txtResCorrectas.setText(utilidades.correctas+"");
        txtResIncorrectas.setText(utilidades.incorrectas+"");
        txtResPuntaje.setText(utilidades.puntaje+"");
        layoutFondo=findViewById(R.id.idLayoutFondo);

        //CopaFragment CopaFr=new CopaFragment();
        //CopaFr.show(getSupportFragmentManager(),"Copa");
        //request= Volley.newRequestQueue(this);
        asignarValoresPreferencias();
        webServiceRegistroR();

        btnInicio=findViewById(R.id.btnHome);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registrarResultador() {
        int jugador= PreferenciasJuego.jugadorId;
        int puntos=utilidades.puntaje;
        String nivel="";
        if(utilidades.nivelJuego==1){
            nivel="Facil";
        }else if(utilidades.nivelJuego==2){
            nivel="Medio";
        }else if(utilidades.nivelJuego==3){
            nivel="Letra Escondida";
        }else if(utilidades.nivelJuego==4){
            nivel="Memoria";
        }else{
            nivel="Razonamiento";
        }
        int correctas=utilidades.correctas;
        int incorrectas=utilidades.incorrectas;
        String modoJuego=PreferenciasJuego.modoJuego;

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(getApplicationContext(),utilidades.NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_ID_JUGADOR,jugador);
        values.put(utilidades.CAMPO_PUNTOS,puntos);
        values.put(utilidades.CAMPO_PALABRAS_CORRECTEAS,correctas);
        values.put(utilidades.CAMPO_PALABRAS_INCORRECTAS,incorrectas);
        values.put(utilidades.CAMPO_NIVEL,nivel);
        values.put(utilidades.CAMPO_MODO_JUEGO,modoJuego);

        Long idResultante=db.insert(utilidades.TABLA_PUNTAJE,utilidades.CAMPO_ID_JUGADOR,values);
        if(idResultante!=-1){
            Toast.makeText(getApplicationContext(),"El puntaje fue registrado ",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void asignarValoresPreferencias() {
        if(PreferenciasJuego.avatarId==1){
            imageAvatar.setImageResource(R.drawable.cara_simio_banner);
        }else {
            imageAvatar.setImageResource(utilidades.listaAvatars.get(PreferenciasJuego.avatarId-1).getAvatarId());
        }

        textNickName.setText(PreferenciasJuego.nickName);
    }

    public void asignarValoresPreferenciasTema(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            layoutFondo.setBackground(getResources().getDrawable(PreferenciasJuego.formaBanner));
        }else {
            layoutFondo.setBackgroundDrawable(getResources().getDrawable(PreferenciasJuego.formaBanner));
        }
        Drawable shape=layoutFondo.getBackground();
        shape.setColorFilter(getResources().getColor(PreferenciasJuego.colorTema), PorterDuff.Mode.SRC);
    }

    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferenciasTema();
    }

    @Override
    public void onBackPressed() {

    }

    //WEBSERVICE____________________________________________________________________________________________________
    private void webServiceRegistroR(){
        int jugador=PreferenciasJuego.jugadorId;
        int puntos=utilidades.puntaje;
        String nivel="";
        String juego="";
        if(utilidades.nivelJuego==1){
            nivel="Atención";
            juego="Stroop1";
        }else if(utilidades.nivelJuego==2){
            nivel="Atención";
            juego="Stroop2";
        }else if(utilidades.nivelJuego==3){
            nivel="Memoria";
            juego="Cantidad Objetos";
        }else if(utilidades.nivelJuego==4){
            nivel="Memoria";
            juego="Memoria";
        }else{
            nivel="Razonamiento";
            juego="Frutimax";
        }
        int correctas=utilidades.correctas;
        int incorrectas=utilidades.incorrectas;
        String modoJuego=PreferenciasJuego.modoJuego;

        progreso=new ProgressDialog(this);
        progreso.setMessage("Registrando...");
        progreso.show();
        String ip=getString(R.string.ip);

        String url=ip+"/TGwebService/registroPuntaje.php?id="+jugador+"&puntos="+puntos+"&correctas="+correctas
                +"&incorrectas="+incorrectas+"&nivel="+nivel+"&modo="+modoJuego+"&juego="+juego;
        url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),"Se registro exitosamente el Puntaje",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No se pudo registrar el Puntaje",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        });
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    }


    //______________________________________________________________________________________________________________
}