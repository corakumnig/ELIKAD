package com.example.cora.elikad_alarmierungsapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cora.elikad_alarmierungsapp.R;

public class InfoAlarmActivity extends AppCompatActivity {

    TextView tv_controllCenter, txt_alarm, txt_operationType, txt_info, txt_street, txt_ort;
    ImageView iv_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alarm);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        tv_controllCenter = (TextView)findViewById(R.id.tv_controllCenter);
        txt_alarm = (TextView)findViewById(R.id.txt_alarm);
        txt_operationType = (TextView)findViewById(R.id.txt_operationType);
        txt_info = (TextView)findViewById(R.id.txt_info);
        txt_street = (TextView)findViewById(R.id.txt_street);
        txt_ort = (TextView)findViewById(R.id.txt_ort);
        iv_alarm = (ImageView)findViewById(R.id.iv_alarm);

        tv_controllCenter.setText(bundle.getString("center"));
        txt_alarm.setText(bundle.getString("controllcenter"));
        txt_operationType.setText(bundle.getString("einsatzart"));
        txt_info.setText(bundle.getString("bemerkung"));
        txt_street.setText(bundle.getString("street"));
        txt_ort.setText(bundle.getString("ort"));
        iv_alarm.setImageResource(R.mipmap.ic_launcher_round);
    }

}
