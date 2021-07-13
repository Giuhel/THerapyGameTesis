package com.helio.therapygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.helio.therapygame.actividades.AcercaDeActivity;
import com.helio.therapygame.actividades.AjustesActivity;
import com.helio.therapygame.actividades.ColoresActivity;
import com.helio.therapygame.actividades.ContendorInstruccionesActivity;
import com.helio.therapygame.clases.ConexionSQLiteHelper;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.utilidades;
import com.helio.therapygame.dialogos.DialogoTipoJuegoFragment;
import com.helio.therapygame.fragments.AreasFragment;
import com.helio.therapygame.fragments.EditarJugadorFragment;
import com.helio.therapygame.fragments.InicioFragment;
import com.helio.therapygame.fragments.RankigFragment;
import com.helio.therapygame.fragments.RegistroJugadorFragment;
import com.helio.therapygame.interfaces.IComunicaFragments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity implements IComunicaFragments{

    Fragment fragmentInicio, registroJugadorFragment,gestionJugadorFragment,
            rankingFragment,EditarJugadorFragment,fragmenAreas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this,R.xml.preferencias,false);
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,this);
        utilidades.obtenerListaAvatars();
        //utilidades.consultarListajugadores(this);
        utilidades.request= Volley.newRequestQueue(this);
        utilidades.consultarListajugadores2(PreferenciasJuego.jugadorId);
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,utilidades.NOMBRE_BD,null,1);

        fragmentInicio =new InicioFragment();
        registroJugadorFragment= new RegistroJugadorFragment();
        //gestionJugadorFragment=new GestionJugadorFragment();
        EditarJugadorFragment=new EditarJugadorFragment();
        rankingFragment=new RankigFragment();
        fragmenAreas=new AreasFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public AlertDialog dialogoGestionUsuario(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Gestionar Jugador").setMessage("Señale si quiere registrar un nuevo jugador" +
                " .\n\n" + "Tambien podra modificar un jugador desde la opcion EDITAR").setNegativeButton("REGISTRAR",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,registroJugadorFragment).commit();
                    }
                })
                .setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(PreferenciasJuego.accionRegistro==1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,EditarJugadorFragment).commit();
                }else{
                    Toast.makeText(getApplicationContext(),"No registro ningun usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return builder.create();
    }

    @Override
    public void IniciarJuego() {
        if(PreferenciasJuego.accionRegistro==1){
            DialogoTipoJuegoFragment dialogoTipoJuego=new DialogoTipoJuegoFragment();
            dialogoTipoJuego.show(getSupportFragmentManager(),"DialogoTipoJuego");
        }else{
            Toast.makeText(this,"Debe Registrar Un Usuario",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void llamarAjustes() {
        Intent intent=new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }

    @Override
    public void consultaRanking() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,rankingFragment).commit();
    }

    @Override
    public void consultaInstrucciones() {
        //Toast.makeText(getApplicationContext(),"iniciar instruccion Activity",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, ContendorInstruccionesActivity.class);
        startActivity(intent);
    }

    @Override
    public void gestionarUsuario() {
        dialogoGestionUsuario().show();
    }

    @Override
    public void consultaInformacion() {
        Intent intent=new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }

    @Override
    public void Areas() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmenAreas).commit();
    }

    @Override
    public void sonidos() {
        Intent intent=new Intent(this, ColoresActivity.class);
        startActivity(intent);
    }

    @Override
    public void mostrarMenu() {
        utilidades.obtenerListaAvatars();
        //utilidades.consultarListajugadores(this);
        utilidades.request= Volley.newRequestQueue(this);
        utilidades.consultarListajugadores2(PreferenciasJuego.jugadorId);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    //controla la pulsacion del boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de Therapy Game?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        
    }


}