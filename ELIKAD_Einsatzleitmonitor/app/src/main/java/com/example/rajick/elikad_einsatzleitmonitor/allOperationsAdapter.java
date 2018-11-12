package com.example.rajick.elikad_einsatzleitmonitor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Operation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class allOperationsAdapter extends BaseAdapter {
    private ArrayList<Operation> operations;
    private LayoutInflater inflater;

    public allOperationsAdapter(Context context, ArrayList<Operation> operations) {
        this.operations = operations;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return operations.size();
    }

    @Override
    public Object getItem(int position) {
        return operations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            // get current veranstaltung
            Operation currenOperation = (Operation) getItem(position);

            // get date and formatting according to my need
            Date datetime = currenOperation.getDatetime();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String strDate = dateFormatter.format(datetime);

            // UI references
            convertView = inflater.inflate(R.layout.all_operations_list_layout, parent, false);
            TextView txt_text = convertView.findViewById(R.id.txt_text);
            TextView txt_caller = convertView.findViewById(R.id.txt_caller);
            TextView txt_alarmLevel = convertView.findViewById(R.id.txt_alarmLevel);


            // set values
            txt_text.setText(strDate + " - " + currenOperation.getText());
            txt_caller.setText(currenOperation.getCaller());
            txt_alarmLevel.setText(String.valueOf(currenOperation.getAlarmLevel()));

            switch(currenOperation.getAlarmLevel())
            {
                case 1:
                    txt_alarmLevel.setTextColor(Color.parseColor("#440000"));
                    break;

                case 2:
                    txt_alarmLevel.setTextColor(Color.parseColor("#640000"));
                    break;

                case 3:
                    txt_alarmLevel.setTextColor(Color.parseColor("#960000"));
                    break;

                case 4:
                    txt_alarmLevel.setTextColor(Color.parseColor("#C80000"));
                    break;

                case 5:
                    txt_alarmLevel.setTextColor(Color.parseColor("#FA0000"));
                    break;
            }
        }
        return convertView;
    }
}
