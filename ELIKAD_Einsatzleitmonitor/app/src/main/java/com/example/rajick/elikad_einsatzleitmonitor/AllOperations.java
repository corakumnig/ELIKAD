package com.example.rajick.elikad_einsatzleitmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Department;
import com.example.rajick.elikad_einsatzleitmonitor.Data.Operation;

import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllOperations extends AppCompatActivity {

    ListView listView_AllOperations;
    TextView txt_Depname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_operations);

        getViewElements();
        setViewElements();
        setAdapterData(getTestData());

    }

    private void getViewElements() {
        listView_AllOperations = findViewById(R.id.listView_allOperations);
        txt_Depname = findViewById(R.id.txt_Depname);
    }

    private void setViewElements(){
        txt_Depname.setText(Department.getName());
    }

    private void setAdapterData(ArrayList<Operation> entries) {
        allOperationsAdapter adapter = new allOperationsAdapter(this, entries);
        listView_AllOperations.setAdapter(adapter);
    }

    private ArrayList<Operation> getTestData(){
        ArrayList<Operation> testData = new ArrayList<>();

        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date datetime = dateFormatter.parse("20.10.2018 13:12", pos);

        testData.add(new Operation(1, "Baum auf Straße", 5, datetime, "Christof"));
        testData.add(new Operation(2, "Baum brennt", 1, datetime, "Cora"));
        testData.add(new Operation(3, "Baum brennt", 3, datetime, "Cora"));
        testData.add(new Operation(4, "Baum brennt", 2, datetime, "Cora"));
        testData.add(new Operation(5, "Baum brennt", 4, datetime, "Cora"));

        return testData;
    }
}
