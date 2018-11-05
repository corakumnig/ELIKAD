package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cora.elikad_alarmierungsapp.R;

import java.util.Arrays;
import java.util.List;


public class AllAlarmsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("All Alarms");


        ListView listView = (ListView)getView().findViewById(R.id.lv_allAlarms);
        String[] testArray = getResources().getStringArray(R.array.lvItems);
        List<String> testList = Arrays.asList(testArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, testList);
        listView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_alarms, container, false);
    }
}
