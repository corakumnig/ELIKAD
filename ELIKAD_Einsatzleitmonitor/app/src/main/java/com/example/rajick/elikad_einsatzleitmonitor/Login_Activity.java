package com.example.rajick.elikad_einsatzleitmonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Department;
import com.google.gson.Gson;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity implements AsyncTaskHandler{

    EditText txtCode;
    AutoCompleteTextView txt_telNr;
    Button btn_signIn;

    SharedPreferences preferences;
    ProgressDialog progDialog;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
    }

    private void initComponents(){
        txtCode = findViewById(R.id.txtCode);
        txt_telNr = findViewById(R.id.txt_telNr);
        btn_signIn = findViewById(R.id.btn_signIn);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    private void initEventHandlers(){
            btn_signIn.setOnClickListener(view -> {
                try {
                    String TelNr = txt_telNr.getText().toString();
                    String Code = txtCode.getText().toString();

                    LoginData lg = new LoginData(TelNr, Code);
                    String json = gson.toJson(lg);

                    AsyncWebserviceTask task = new AsyncWebserviceTask("POST", "login", Login_Activity.this, getApplicationContext());
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
            case 202:
                Department department = gson.fromJson(content, Department.class);
                preferences.edit().putString("DepName", department.getName()).apply();
                preferences.edit().putString("DepId", Integer.toString(department.getId())).apply();
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
                Toast.makeText(this, "Server error", Toast.LENGTH_SHORT).show();
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
