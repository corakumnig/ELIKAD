package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cora.elikad_alarmierungsapp.Data.Location;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.Data.OperationType;
import com.example.cora.elikad_alarmierungsapp.Data.RVAdapter;
import com.example.cora.elikad_alarmierungsapp.Data.allOperationsAdapter;
import com.example.cora.elikad_alarmierungsapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.Inflater;

public class AllAlarmsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    ArrayList<Operation> operationExamples;
    RecyclerView rv;
    RVAdapter operationsAdapter;
    View view;

    public AllAlarmsFragment() {}

    public static AllAlarmsFragment newInstance() {
        AllAlarmsFragment fragment = new AllAlarmsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_alarms, container, false);
        getActivity().setTitle("All Alarms");

        rv = (RecyclerView) view.findViewById(R.id.rv_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        operationExamples = new ArrayList<>();
        operationExamples.add(new Operation(new Date(2018,12,01, 9,59), OperationType.BRANDEINSATZ, "Chrisi", "Haus brennt", "4", new Location("01", "Beispielstraße", "9000", "Feldkirchen", "Kärnten"), "FF Himmelberg"));
        operationExamples.add(new Operation(new Date(2018,11,29, 4, 07), OperationType.VERKEHRSUNFALL, "Kristian", "zwei Autos frontal ineinander", "3", new Location("02", "Examplegasse", "9000", "Villach", "Kärnten"), "FF Villach"));
        operationExamples.add(new Operation(new Date(2018,10,10, 21, 25), OperationType.TAUCHEINSATZ, "Hans", "bewusstlos im Wasser", "4", new Location("03", "Musterstraße", "9800", "Millstatt", "Kärnten"), "ÖWR Millstatt"));

        operationsAdapter = new RVAdapter(operationExamples, getContext());
        rv.setAdapter(operationsAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
