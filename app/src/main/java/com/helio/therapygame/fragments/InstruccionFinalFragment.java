package com.helio.therapygame.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.helio.therapygame.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InstruccionFinalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstruccionFinalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, helio.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnFinInstruccion;
    View vista;
    Activity actividad;


    public InstruccionFinalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstruccionFinalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InstruccionFinalFragment newInstance(String param1, String param2) {
        InstruccionFinalFragment fragment = new InstruccionFinalFragment();
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
        vista=inflater.inflate(R.layout.fragment_instruccion_final, container, false);
        btnFinInstruccion=vista.findViewById(R.id.btnFinInstruccion);

        btnFinInstruccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actividad.finish();
            }
        });
        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            actividad = (Activity) context;
        }

    }
}