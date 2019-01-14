package com.example.rajick.elikad_einsatzleitmonitor;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllOperations extends SharedClass implements AsyncTaskHandler{

    ListView listView_AllOperations;
    TextView txt_Depname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_operations);

        initComponents();
        setViewElements();

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

    private void loadAllOperations(){
        try {
            String route = "department/" + preferences.getString("DepId", "Default") + "/operations";
            AsyncWebserviceTask task = new AsyncWebserviceTask("POST", route, AllOperations.this, getApplicationContext());
            task.execute(null, null);

        } catch (Exception ex) {
            Toast.makeText(AllOperations.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    @Override
    public void onPreExecute() {
        progDialog = new ProgressDialog(AllOperations.this);
        progDialog.setMessage("Loading...");
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(true);
        progDialog.show();
    }

    @Override
    public void onSuccess(int statusCode, String content) {
        switch (statusCode) {
            case 200:

                ArrayList<Operation> operationList = gson.fromJson(content, new TypeToken<ArrayList<Operation>>(){}.getType());
                setAdapterData(operationList);
                
                break;

            case 404:
                Toast.makeText(this, "Could't connect", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Error at loading. Restart please", Toast.LENGTH_SHORT).show();
        }

        progDialog.dismiss();
    }

    @Override
    public void onError(Error err) {
        progDialog.cancel();
        Toast.makeText(AllOperations.this, "Something went wrong", Toast.LENGTH_LONG).show();
        err.printStackTrace();
    }
}
