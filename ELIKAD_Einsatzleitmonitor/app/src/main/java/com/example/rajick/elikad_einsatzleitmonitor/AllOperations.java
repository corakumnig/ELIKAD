package com.example.rajick.elikad_einsatzleitmonitor;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Department;
import com.example.rajick.elikad_einsatzleitmonitor.Data.Operation;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllOperations extends SharedClass {

    ListView listView_AllOperations;
    TextView txt_Depname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_operations);

        initComponents();
        setViewElements();
        setAdapterData(getTestData());

        Toast.makeText(this, AsyncWebserviceTask.getAccesToken(), Toast.LENGTH_SHORT).show();
        System.out.println("Test1" + preferences.getString("DepName", "default"));
        System.out.println("Test2" + preferences.getString("DepId", "default"));
    }

    private void initComponents() {
        listView_AllOperations = findViewById(R.id.listView_allOperations);
        txt_Depname = findViewById(R.id.txt_Depname);
    }

    private void setViewElements(){
        txt_Depname.setText(preferences.getString("DepName", "Default"));
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
