package com.example.rajick.elikad_einsatzleitmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Operation;

import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllOperations extends AppCompatActivity {

    ListView listView_AllOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_operations);

        getViewElements();
        setAdapterData(getTestData());
    }

    private void getViewElements() {
        listView_AllOperations = findViewById(R.id.listView_allOperations);
    }

    private void setAdapterData(ArrayList<Operation> entries) {
        allOperationsAdapter adapter = new allOperationsAdapter(this, entries);
        //ArrayAdapter<Veranstaltung> adapter = new ArrayAdapter<Veranstaltung>(getActivity(), android.R.layout.simple_list_item_1, entries);
        listView_AllOperations.setAdapter(adapter);
    }

    private ArrayList<Operation> getTestData(){
        ArrayList<Operation> testData = new ArrayList<>();

        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date datetime = dateFormatter.parse("20.10.2018 13:12", pos);

        testData.add(new Operation(1, "Baum auf Straße", 5, datetime, "Christof"));
        testData.add(new Operation(2, "Baum brennt", 5, datetime, "Cora"));

        return testData;
    }
}
