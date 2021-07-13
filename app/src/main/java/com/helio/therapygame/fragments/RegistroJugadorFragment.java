package com.helio.therapygame.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.helio.therapygame.R;
import com.helio.therapygame.adapters.AdaptadorAvatar;
import com.helio.therapygame.clases.ConexionSQLiteHelper;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.VolleySingleton;
import com.helio.therapygame.clases.utilidades;
import com.helio.therapygame.clases.vo.JugadorVO;
import com.helio.therapygame.interfaces.IComunicaFragments;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroJugadorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, helio.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerAvatars;
    View vista;
    Activity actividad;
    IComunicaFragments iComunicaFragments;

    ImageButton btnAtras;
    ImageButton btnAyuda;

    FloatingActionButton fabRegistro;
    EditText campoNick;
    RadioButton radioM,radioF;
    RelativeLayout layoutFondo;

    //paraWEbService
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    RequestQueue request2;
    JsonObjectRequest jsonObjectRequest2;

    TextView codigo;




    public RegistroJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroJugadorFragment newInstance(String param1, String param2) {
        RegistroJugadorFragment fragment = new RegistroJugadorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_registro_jugador, container, false);
        recyclerAvatars=vista.findViewById(R.id.recyclerAvatarsId);
        fabRegistro=vista.findViewById(R.id.idFabRegistro);
        campoNick=vista.findViewById(R.id.campoNickname);
        radioF=vista.findViewById(R.id.radioF);
        radioM=vista.findViewById(R.id.radioM);
        btnAtras=vista.findViewById(R.id.btnIcoAtras);
        layoutFondo=vista.findViewById(R.id.idLayoutFondo);
        btnAyuda=vista.findViewById(R.id.btnAyuda);
        codigo=vista.findViewById(R.id.txtcodigo);

        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recyclerAvatars.setHasFixedSize(true);
        //request= Volley.newRequestQueue(getContext());

        obtenercodigo();

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                createSimpleDialog().show();
            }
        });

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebserviceRegistro();
                //registrarJugador();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComunicaFragments.mostrarMenu();
                campoNick.setText("");
            }
        });

        final AdaptadorAvatar miAdaptador = new AdaptadorAvatar(utilidades.listaAvatars);
        recyclerAvatars.setAdapter(miAdaptador);
        return vista;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  Activity) {
            this.actividad= (Activity) context;
            iComunicaFragments= (IComunicaFragments) context;
        }
    }

    private void registrarJugador(){
        String genero="";

        if (radioM.isChecked()==true){
            genero="1";
        }else if(radioF.isChecked()==true){
            genero="2";
        }else {
            genero="No Seleccionado";
        }

        if (!genero.equals("No Seleccionado") && campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals("")){

            int avatarId=utilidades.avatarSeleccion.getId();
            String nickName=campoNick.getText().toString();

            String registro="Nombre: "+campoNick.getText().toString()+"\n";
            registro+="GEnero: "+genero+"\n";
            registro+="Avatar ID: "+utilidades.avatarSeleccion.getId();

            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,utilidades.NOMBRE_BD,null,1);
            SQLiteDatabase db=conn.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(utilidades.CAMPO_NOMBRE,nickName);
            values.put(utilidades.CAMPO_GENERO,genero);
            values.put(utilidades.CAMPO_AVATAR,avatarId);

            Long idResultante=db.insert(utilidades.TABLA_JUGADOR,utilidades.CAMPO_ID,values);

            if(idResultante!=-1){
                System.out.println("REgistrar: "+registro);
                Toast.makeText(actividad,"Se registro exitosamente\n"+idResultante+" - "+registro,Toast.LENGTH_SHORT).show();
                PreferenciasJuego.jugadorId=Integer.parseInt(idResultante+"");
                PreferenciasJuego.nickName="{ "+campoNick.getText().toString()+" }";
                PreferenciasJuego.avatarId=utilidades.avatarSeleccion.getId();

                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(actividad);
                PreferenciasJuego.asignarPreferenciasJugador(preferences,actividad);
                campoNick.setText("");
                iComunicaFragments.mostrarMenu();

            }else{
                Toast.makeText(actividad,"No se pudo registrar el jugador \n"+registro,Toast.LENGTH_SHORT).show();
            }

            db.close();

        }else{
            Toast.makeText(actividad,"Verifique los datos de Registro! ",Toast.LENGTH_SHORT).show();
        }
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);

        builder.setTitle("Ayuda")
                .setMessage("Ingrese el Nickname, Genero y seleccione el Avatar del jugador de la lista de avatars disponibles, posteriormente presione el botón para confirmar el registro.")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }


    ///metodos para webSErVice

    private void cargarWebserviceRegistro() {
        String genero="";
        if(radioM.isChecked()){
            genero="1";
        }else if(radioF.isChecked()){
            genero="2";
        }else {
            genero="No Seleccionado";
        }
        int avatarId=utilidades.avatarSeleccion.getId();
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        String ip=getString(R.string.ip);
        String url=ip+"/TGwebService/registrojugador.php?id="+Integer.parseInt(codigo.getText().toString())+"&nombre="+campoNick.getText().toString()+"&genero="+genero+"&avatar="+avatarId;
        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getContext(),"Se registro exitosamente",Toast.LENGTH_SHORT).show();
                progreso.hide();
                PreferenciasJuego.jugadorId= Integer.parseInt(codigo.getText().toString());
                PreferenciasJuego.nickName="{ "+campoNick.getText().toString()+" }";
                PreferenciasJuego.avatarId=utilidades.avatarSeleccion.getId();
                PreferenciasJuego.accionRegistro=1;
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(actividad);
                PreferenciasJuego.asignarPreferenciasJugador(preferences,actividad);
                campoNick.setText("");
                iComunicaFragments.mostrarMenu();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();
                Toast.makeText(getContext(),"No se pudo registrar el jugador"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    //obtener codigo siguiente
    private void obtenercodigo() {
        String ip=getString(R.string.ip);
        String url2=ip+"/TGwebService/codsiguiente.php";

        jsonObjectRequest2=new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JugadorVO miUsuario = new JugadorVO();
                JSONArray json=response.optJSONArray("Id");
                JSONObject jsonObject=null;
                try {
                    jsonObject=json.getJSONObject(0);
                    miUsuario.setId(jsonObject.optInt("codigo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                codigo.setText(miUsuario.getId()+"");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"error"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest2);
    }


}