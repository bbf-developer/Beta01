package com.project.ignacio_rvf_bbf.bbf_reporter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProximaPlantaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProximaPlantaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProximaPlantaFragment extends Fragment {

    RecyclerViewAdaptador adapter;

    ArrayList<ProximoModelo> listaVisita;

    RecyclerView recyclerViewProximo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProximaPlantaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProximaPlantaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProximaPlantaFragment newInstance(String param1, String param2) {
        ProximaPlantaFragment fragment = new ProximaPlantaFragment();
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
        View view = inflater.inflate(R.layout.fragment_proxima_planta, container, false);

        //OLD
        // recyclerViewProximo= view.findViewById(R.id.recyclerview);
        // recyclerViewProximo.setLayoutManager(new LinearLayoutManager(getContext()));

        /***/
        listaVisita = new ArrayList<>();
        recyclerViewProximo = view.findViewById(R.id.recyclerview);
        recyclerViewProximo.setLayoutManager(new LinearLayoutManager(getActivity()));

        llenarLista();

        RecyclerViewAdaptador adapter = new RecyclerViewAdaptador(listaVisita);
        recyclerViewProximo.setAdapter(adapter);

        recyclerViewProximo.setHasFixedSize(true);

        /***/

        return view;
    }

    private void llenarLista() {
        listaVisita.add(new ProximoModelo(1,"ARAUCO S.A.","Reparar Nariz de Toro","18-01-2018", R.drawable.arauco_logo));
        listaVisita.add(new ProximoModelo(1,"COLBUN S.A.","Ispeccionar Tuberias","18-01-2018", R.drawable.colbun_logo));
        listaVisita.add(new ProximoModelo(1,"ARAUCO S.A.","Reparar Caldera","18-01-2018",  R.drawable.arauco_logo));
        listaVisita.add(new ProximoModelo(1,"COLBUN S.A.","Ispeccionar Caldera","18-01-2018",  R.drawable.colbun_logo));

     }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
