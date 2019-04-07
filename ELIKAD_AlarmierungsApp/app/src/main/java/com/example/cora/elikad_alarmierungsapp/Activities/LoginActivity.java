package com.example.cora.elikad_alarmierungsapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cora.elikad_alarmierungsapp.Data.AsyncTaskHandler;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncWebserviceTask;
import com.example.cora.elikad_alarmierungsapp.Data.LoginData;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.R;
import com.google.gson.Gson;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AsyncTaskHandler {

    // UI references.
    private AutoCompleteTextView mTelefoneNrView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ProgressDialog progDialog;
    private Gson gson;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTelefoneNrView = (AutoCompleteTextView) findViewById(R.id.txt_telNr);
        mPasswordView = (EditText) findViewById(R.id.txt_password);
        gson = new Gson();
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String phonenumber = mTelefoneNrView.getText().toString();
                    String pin = mPasswordView.getText().toString();

                    LoginData lg = new LoginData(phonenumber, pin);

                    System.out.println("Test 1: " + lg);

                    String json = gson.toJson(lg);

                    AsyncWebserviceTask task = new AsyncWebserviceTask("POST", "login/member", LoginActivity.this, getApplicationContext());

                    task.execute(null, json);

                } catch (Exception ex) {
                    Toast.makeText(LoginActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void onPreExecute() {
        progDialog = new ProgressDialog(LoginActivity.this);
        progDialog.setMessage("Logging in...");
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(true);
        progDialog.show();
    }

    @Override
    public void onSuccess(int statusCode, String content) {
        System.out.println("START TEST: " + statusCode);
        switch (statusCode) {
            case 200:
                //+435647345382
                //12345678

                //+435647345383
                System.out.println("Test 2: " + content);

                Member member = gson.fromJson(content, Member.class);

                System.out.println("TEST Login Member: " + member);

                preferences.edit().putInt("MemberId", member.getId()).commit();
                preferences.edit().putString("MemberFirstName", member.getFirstname()).commit();
                preferences.edit().putString("MemberLastName", member.getLastname()).commit();
                preferences.edit().putString("MemberEmail", member.getEmail()).commit();
                preferences.edit().putString("MemberPhonenumber", member.getPhonenumber()).commit();
                preferences.edit().putInt("MemberIdDepartment", member.getIdDepartment()).commit();
                preferences.edit().putBoolean("MemberIsCommanda", member.getIsCommanda()).commit();
                preferences.edit().putString("MemberSvnr", member.getSvNr()).commit();
                preferences.edit().putString("MemberPin", member.getPin()).commit();
                preferences.edit().putString("MemberGender", member.getGender()).commit();

                //preferences.edit().putBoolean("MemberIsCommanda", true).commit();

                System.out.println("Test Member: " + member.toString());

                startActivity(new Intent(this, NavigationActivity.class));
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
        //Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        err.printStackTrace();
    }
}

