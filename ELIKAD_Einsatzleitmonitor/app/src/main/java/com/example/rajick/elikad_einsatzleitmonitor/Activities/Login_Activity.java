package com.example.rajick.elikad_einsatzleitmonitor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.rajick.elikad_einsatzleitmonitor.Backend.AsyncTaskHandler;
import com.example.rajick.elikad_einsatzleitmonitor.Backend.AsyncWebserviceTask;
import com.example.rajick.elikad_einsatzleitmonitor.Data.Department;
import com.example.rajick.elikad_einsatzleitmonitor.Data.LoginData;
import com.example.rajick.elikad_einsatzleitmonitor.R;
import com.example.rajick.elikad_einsatzleitmonitor.Misc.SharedClass;

import android.widget.Toast;

public class Login_Activity extends SharedClass implements AsyncTaskHandler {

    EditText txtCode;
    AutoCompleteTextView txt_telNr;
    Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        initComponents();
        initEventHandlers();
        AsyncWebserviceTask.resetAccessToken();
    }

    private void initComponents(){
        txtCode = findViewById(R.id.txtCode);
        txt_telNr = findViewById(R.id.txt_telNr);
        btn_signIn = findViewById(R.id.btn_signIn);
    }

    private void initEventHandlers(){
            btn_signIn.setOnClickListener(view -> {
                try {
                    String TelNr = txt_telNr.getText().toString();
                    String Code = txtCode.getText().toString();

                    LoginData lg = new LoginData(TelNr, Code);

                    String json = gson.toJson(lg);

                    AsyncWebserviceTask task = new AsyncWebserviceTask("POST", "login/department", Login_Activity.this, getApplicationContext());
                    task.execute(null, json);

                } catch (Exception ex) {
                    Toast.makeText(Login_Activity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            });
    }

    @Override
    public void onPreExecute() {
        progDialog = new ProgressDialog(Login_Activity.this);
        progDialog.setMessage("Logging in...");
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(true);
        progDialog.show();
    }

    @Override
    public void onSuccess(int statusCode, String content) {
        switch (statusCode) {
            case 200:
                Department department = gson.fromJson(content, Department.class);

                preferences.edit().putString("DepName", department.getName()).commit();
                preferences.edit().putString("DepId", Integer.toString(department.getId())).commit();

                startActivity(new Intent(this, AllOperations.class));
                finish();
                break;

            case 403:
                Toast.makeText(this, "Wrong Username or password", Toast.LENGTH_SHORT).show();
                break;

            case 404:
                Toast.makeText(this, "Could't connect", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Login Error: Try again.", Toast.LENGTH_SHORT).show();
        }

        progDialog.dismiss();
    }

    @Override
    public void onError(Error err) {
        progDialog.cancel();
        Toast.makeText(Login_Activity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        err.printStackTrace();
    }
}
