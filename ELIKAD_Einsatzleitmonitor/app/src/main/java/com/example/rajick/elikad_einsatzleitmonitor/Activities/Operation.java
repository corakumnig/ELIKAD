package com.example.rajick.elikad_einsatzleitmonitor.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajick.elikad_einsatzleitmonitor.Adapters.AcceptedAdapter;
import com.example.rajick.elikad_einsatzleitmonitor.Backend.AsyncTaskHandler;
import com.example.rajick.elikad_einsatzleitmonitor.Backend.AsyncWebserviceTask;
import com.example.rajick.elikad_einsatzleitmonitor.Data.Member;
import com.example.rajick.elikad_einsatzleitmonitor.Data.MemberFunctions;
import com.example.rajick.elikad_einsatzleitmonitor.Misc.SharedClass;
import com.example.rajick.elikad_einsatzleitmonitor.R;
import com.google.gson.reflect.TypeToken;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Operation extends SharedClass implements AsyncTaskHandler {

    //ToDo: api/operations/:ID/members
    ListView list_Member;
    TextView txt_acceptCount, txt_ELCount, txt_ASCount, txt_MACount, txt_SACount, txt_Info, txt_text;

    ArrayList<Member> allMembers;
    com.example.rajick.elikad_einsatzleitmonitor.Data.Operation currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        currentOperation = gson.fromJson(preferences.getString("currentOperation", "{}"), com.example.rajick.elikad_einsatzleitmonitor.Data.Operation.class);
        getViewElements();
        loadAllMembers();
    }

    private void getViewElements() {
        list_Member = findViewById(R.id.list_Member);
        txt_acceptCount = findViewById(R.id.txt_acceptCount);
        txt_ELCount = findViewById(R.id.txt_ELCount);
        txt_ASCount = findViewById(R.id.txt_ASCount);
        txt_MACount = findViewById(R.id.txt_MACount);
        txt_SACount = findViewById(R.id.txt_SACount);
        txt_Info = findViewById(R.id.txt_Info);
        txt_text = findViewById(R.id.txt_text);
    }

    private void setAdapterData(ArrayList<Member> entries) {
        AcceptedAdapter adapter = new AcceptedAdapter(this, entries);
        list_Member.setAdapter(adapter);
    }

    private void loadAllMembers(){
        try {
            String route = "operations/" + currentOperation.getId() + "/members";
            AsyncWebserviceTask task = new AsyncWebserviceTask("POST", route, Operation.this, getApplicationContext());
            task.execute(null, null);

        } catch (Exception ex) {
            Toast.makeText(Operation.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public void onPreExecute() {
        progDialog = new ProgressDialog(Operation.this);
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
                allMembers = gson.fromJson(content, new TypeToken<ArrayList<Member>>(){}.getType());
                setAdapterData(allMembers);

                increaseCounters();

                txt_Info.setText(currentOperation.getText());
                txt_text.setText(currentOperation.getText());
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
        Toast.makeText(Operation.this, "Something went wrong", Toast.LENGTH_LONG).show();
        err.printStackTrace();
    }

    private void increaseCounters(){
        for(Member m : allMembers){

            txt_acceptCount.setText((new Integer(txt_acceptCount.getText().toString()) + 1) + "");
            System.out.println("Test 1" + m.getFunction());
            switch(m.getFunction()){
                case "Einsatzleiter":
                    txt_ELCount.setText((new Integer(txt_ELCount.getText().toString()) + 1) + "");
                    break;

                case "Maschinist":
                    txt_MACount.setText((new Integer(txt_MACount.getText().toString()) + 1) + "");
                    break;

                case "Atemschutz":
                    txt_ASCount.setText((new Integer(txt_ASCount.getText().toString()) + 1) + "");
                    break;

                case "Sanitäter":
                    txt_SACount.setText((new Integer(txt_SACount.getText().toString()) + 1) + "");
                    break;

                default:
            }
        }
    }
}
