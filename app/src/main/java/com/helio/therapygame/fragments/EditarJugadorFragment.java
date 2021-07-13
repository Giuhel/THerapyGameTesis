package com.helio.therapygame.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.helio.therapygame.adapters.AdaptadorJugador;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.VolleySingleton;
import com.helio.therapygame.clases.utilidades;
import com.helio.therapygame.clases.vo.JugadorVO;
import com.helio.therapygame.interfaces.IComunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarJugadorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, helio.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Activity actividad;
    View vista;
    IComunicaFragments iComunicaFragments;
    FloatingActionsMenu grupBotones;
    FloatingActionButton fabActualizar;

    RecyclerView recyclerAvatars,recyclerJugadores;

    ImageButton btnAtras,btnAyuda;
    TextView barraSeleccion;
    EditText campoNick;
    RadioButton radioM,radioF;

    JugadorVO jugadorSeleccionado;
    RelativeLayout layoutFondo;

    ProgressDialog progreso;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;

    public EditarJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarJugadorFragment newInstance(String param1, String param2) {
        EditarJugadorFragment fragment = new EditarJugadorFragment();
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
        vista=inflater.inflate(R.layout.fragment_editar_jugador, container, false);

        recyclerAvatars =vista.findViewById(R.id.recyclerAvatarsId);
        recyclerJugadores =vista.findViewById(R.id.recyclerJugadorId);

        btnAtras=vista.findViewById(R.id.btnIcoAtrass);
        grupBotones=vista.findViewById(R.id.grupoFab);
        fabActualizar=vista.findViewById(R.id.idFabActualizar);


        campoNick=vista.findViewById(R.id.campoNickName);
        radioM=vista.findViewById(R.id.radioM);
        radioF=vista.findViewById(R.id.radioF);
        barraSeleccion=vista.findViewById(R.id.barraSeleccionId);
        layoutFondo=vista.findViewById(R.id.idLayoutFondo);

        recyclerJugadores.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerJugadores.setHasFixedSize(true);

        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recyclerAvatars.setHasFixedSize(true);

        btnAyuda=vista.findViewById(R.id.btnAyuda);

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSimpleDialog().show();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComunicaFragments.mostrarMenu();
                campoNick.setText("");
            }
        });

        fabActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarwebService();


            }
        });


        llenarAdaptadorJugador();
        llenarAdaptadorAvatars(0);

        return vista;
    }


    public androidx.appcompat.app.AlertDialog createSimpleDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(actividad);

        builder.setTitle("Ayuda")
                .setMessage("Elija el jugador de la lista de Jugadores disponible, si lo desea puede modificar su información (Nick, Genero, Avatar) y posteriormente" +
                        "desde el menú de botones flotantes podrá actualizarlo o seleccionarlo para Jugar.")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        return builder.create();
    }

    private void llenarAdaptadorJugador() {
        //se asigna la lista de jugadores por defecto
        final AdaptadorJugador miAdaptadorJugadores=new AdaptadorJugador(utilidades.listaJugadores);
        miAdaptadorJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupBotones.collapse();
                jugadorSeleccionado=utilidades.listaJugadores.get(recyclerJugadores.getChildAdapterPosition(view));
                campoNick.setText(jugadorSeleccionado.getNombre());
                if(jugadorSeleccionado.getGenero().equals("1")){
                    radioM.setChecked(true);
                }else{
                    radioF.setChecked(true);
                }
                utilidades.avatarSeleccion=utilidades.listaAvatars.get(jugadorSeleccionado.getAvatar()-1);
                llenarAdaptadorAvatars(jugadorSeleccionado.getAvatar());
            }
        });
        recyclerJugadores.setAdapter(miAdaptadorJugadores);
    }

    private void llenarAdaptadorAvatars(int avatarId) {
        utilidades.avatarIdSeleccion=avatarId;

        AdaptadorAvatar miAdaptadorAvatars=new AdaptadorAvatar(utilidades.listaAvatars);
        recyclerAvatars.scrollToPosition(avatarId-1);
        recyclerAvatars.setAdapter(miAdaptadorAvatars);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad= (Activity) context;
            iComunicaFragments= (IComunicaFragments) this.actividad;
        }
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }


    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }


    ///webservice-------------------------------------------------

    private void actualizarwebService(){
        String genero="";
        if (radioM.isChecked()==true){
            genero="1";
        }else if(radioF.isChecked()==true){
            genero="2";
        }else {
            genero="No Seleccionado";
        }

        int avatarId=utilidades.avatarSeleccion.getId();
        String nickName=campoNick.getText().toString();

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        String ip=getString(R.string.ip);
        String url=ip+"/TGwebService/actualizarJugador.php?id="+jugadorSeleccionado.getId()+"&nombre="+nickName+"&genero="+genero+"&avatar="+avatarId;
        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getContext(),"Se actualizo exitosamente",Toast.LENGTH_SHORT).show();
                progreso.hide();
                grupBotones.collapse();
                llenarAdaptadorJugador();
                PreferenciasJuego.nickName=nickName;
                PreferenciasJuego.avatarId=utilidades.avatarSeleccion.getId();
                PreferenciasJuego.jugadorId=jugadorSeleccionado.getId();
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(actividad);
                PreferenciasJuego.asignarPreferenciasJugador(preferences,actividad);
                grupBotones.collapse();
                campoNick.setText("");
                iComunicaFragments.mostrarMenu();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();
                Toast.makeText(getContext(),"No se pudo actualizar el jugador"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    //-----------------------------------------------------------------

    private void esperar(int s){
        try {
            Thread.sleep(s * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}