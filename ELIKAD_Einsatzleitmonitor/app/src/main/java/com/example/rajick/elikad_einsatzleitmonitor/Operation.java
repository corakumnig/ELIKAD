package com.example.rajick.elikad_einsatzleitmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Member;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Operation extends AppCompatActivity {

    ListView list_Member;

    ArrayList<Member> allMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        getViewElements();
        setAdapterData(getTestData());
    }

    private void getViewElements() {
        list_Member = findViewById(R.id.list_Member);
    }

    private void setAdapterData(ArrayList<Member> entries) {
        AcceptedAdapter adapter = new AcceptedAdapter(this, entries);
        list_Member.setAdapter(adapter);
    }

    private void setFunctionCounters(){
    }
    private ArrayList<Member> getTestData(){
        ArrayList<Member> testData = new ArrayList<>();

        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date datetime = dateFormatter.parse("20.10.2018 13:12", pos);

        testData.add(new Member("Franz", "Gscheid"));
        testData.add(new Member("Josef", "Dumm"));
        testData.add(new Member("Stizzi", "Geht"));

        return testData;
    }
}
