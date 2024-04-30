package com.helio.therapygame.fragments;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.helio.therapygame.R;
import com.helio.therapygame.clases.PreferenciasJuego;
import com.helio.therapygame.clases.VolleySingleton;
import com.helio.therapygame.clases.vo.Barras;
import com.helio.therapygame.interfaces.IComunicaFragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AreasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AreasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, helio.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    IComunicaFragments iComunicaFragments;
    ProgressBar pbGeneral,pbAtencion,pbMemoria,pbRazonamiento;
    int counter =0;
    ImageButton btnAtras;
    Activity actividad;
    RelativeLayout layoutFondo;
    TextView atencion,memoria,razonamiento,general;
    ObjectAnimator progresoAnimator;

    JsonObjectRequest jsonObjectRequest;
    final Handler handler=new Handler();

    public AreasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AreasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AreasFragment newInstance(String param1, String param2) {
        AreasFragment fragment = new AreasFragment();
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
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_areas, container, false);

        pbGeneral=vista.findViewById(R.id.PbGeneral);
        pbAtencion=vista.findViewById(R.id.PbAtencion);
        pbMemoria=vista.findViewById(R.id.PbMemoria);
        pbRazonamiento=vista.findViewById(R.id.PbRazonamiento);
        btnAtras=vista.findViewById(R.id.btnIcoAtras);
        layoutFondo=vista.findViewById(R.id.idLayoutFondo);
        atencion=vista.findViewById(R.id.atencion);
        memoria=vista.findViewById(R.id.memoria);
        razonamiento=vista.findViewById(R.id.razonamiento);
        general=vista.findViewById(R.id.general);

        BarraAtencion();
        BarraMemoria();
        BarraRazonamiento();
        BarraGeneral();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComunicaFragments.mostrarMenu();
            }
        });

        return  vista;
    }

    private void cargabarra(){
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run()
            {
                counter++;
                pbGeneral.setProgress(counter);
                //pbAtencion.setProgress(counter);
                pbMemoria.setProgress(counter);
                pbRazonamiento.setProgress(counter);

                if(counter == 100)
                    t.cancel();
            }
        };

        t.schedule(tt,0,100);
    }

    private void init(){
        progresoAnimator=ObjectAnimator.ofInt(pbAtencion,"progress",0, 20);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaFragments= (IComunicaFragments) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }


    private void BarraAtencion() {
        int codigo=PreferenciasJuego.jugadorId;
        String ip=getString(R.string.ip);

        String url=ip+"/TGwebService/barraAtencion.php?id="+codigo;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Barras barras = new Barras();
                JSONArray json=response.optJSONArray("barra");
                JSONObject jsonObject=null;
                try {
                    jsonObject=json.getJSONObject(0);
                    barras.setPbAtencion(jsonObject.optInt("puntos"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                atencion.setText(barras.getPbAtencion()+"");
                pbAtencion.setProgress(barras.getPbAtencion());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"error"+error.toString(),Toast.LENGTH_LONG).show();
                atencion.setText("0");
                pbAtencion.setProgress(0);
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void BarraMemoria() {
        int codigo=PreferenciasJuego.jugadorId;
        String ip=getString(R.string.ip);
        String url2=ip+"/TGwebService/barraMemoria.php?id="+codigo;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Barras barras = new Barras();
                JSONArray json=response.optJSONArray("barra");
                JSONObject jsonObject=null;
                try {
                    jsonObject=json.getJSONObject(0);
                    barras.setPbMemoria(jsonObject.optInt("puntos"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                memoria.setText(barras.getPbMemoria()+"");
                pbMemoria.setProgress(barras.getPbMemoria());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"error"+error.toString(),Toast.LENGTH_LONG).show();
                memoria.setText("0");
                pbMemoria.setProgress(0);
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void BarraRazonamiento() {
        int codigo=PreferenciasJuego.jugadorId;
        String ip=getString(R.string.ip);
        String url2=ip+"/TGwebService/barraRazonamiento.php?id="+codigo;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Barras barras = new Barras();
                JSONArray json=response.optJSONArray("barra");
                JSONObject jsonObject=null;
                try {
                    jsonObject=json.getJSONObject(0);
                    barras.setPbRazonamiento(jsonObject.optInt("puntos"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                razonamiento.setText(barras.getPbRazonamiento()+"");
                pbRazonamiento.setProgress(barras.getPbRazonamiento());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"error"+error.toString(),Toast.LENGTH_LONG).show();
                razonamiento.setText("0");
                pbRazonamiento.setProgress(0);
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void BarraGeneral() {
        int codigo=PreferenciasJuego.jugadorId;
        String ip=getString(R.string.ip);
        String url2=ip+"/TGwebService/barraGeneral.php?id="+codigo;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Barras barras = new Barras();
                JSONArray json=response.optJSONArray("barra");
                JSONObject jsonObject=null;
                try {
                    jsonObject=json.getJSONObject(0);
                    barras.setPbGeneral(jsonObject.optInt("puntos"));
                } catch (JSONException helio) {
                    helio.printStackTrace();
                }
                general.setText(barras.getPbGeneral()+"");
                pbGeneral.setProgress(barras.getPbGeneral());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"error"+error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void barraGeneral2(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int a= Integer.parseInt(atencion.getText().toString());
                int m= Integer.parseInt(memoria.getText().toString());
                int r= Integer.parseInt(razonamiento.getText().toString());
                int  s=0;
                if(a!=0&&m!=0&&r!=0){
                    s=(a+m+r)/3;
                    general.setText(s+"");
                    pbGeneral.setProgress(s);
                }
            }
        },800);
    }
}