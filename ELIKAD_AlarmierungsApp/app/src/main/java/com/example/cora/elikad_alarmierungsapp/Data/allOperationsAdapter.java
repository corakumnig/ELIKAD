package com.example.cora.elikad_alarmierungsapp.Data;

import android.content.Context;
import android.media.Image;
import android.media.VolumeShaper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cora.elikad_alarmierungsapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class allOperationsAdapter extends ArrayAdapter<Operation> {

    private ArrayList<Operation> operations;
    private Context con;

    public allOperationsAdapter(ArrayList<Operation> operations, Context con) {
        super(con, R.layout.adapter_operation, operations);
        this.operations = operations;
        this.con = con;
    }

    private static class ViewHolder {
        ImageView iv_bell;
        TextView txt_controlCenter;
        TextView txt_alarmType;
        TextView txt_location;
        TextView txt_date;
    }

    @Override
    public int getCount() {
        return operations.size();
    }

    @Override
    public Operation getItem(int position) {
        return operations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get current veranstaltung
        Operation currenOperation = (Operation) getItem(position);
        ViewHolder viewHolder;
        String strDate = "";
        final View result;

        if(convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_operation, parent, false);
            // get date and formatting according to my need
            Date datetime = currenOperation.getDatetime();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            strDate = dateFormatter.format(datetime);

            // UI references
            convertView = inflater.inflate(R.layout.adapter_operation, parent, false);
            viewHolder.iv_bell = convertView.findViewById(R.id.cv_iv_bell);
            viewHolder.txt_controlCenter = convertView.findViewById(R.id.cv_tv_controllCenter);
            viewHolder.txt_alarmType = convertView.findViewById(R.id.cv_iv_alarmtyp);
            viewHolder.txt_location = convertView.findViewById(R.id.cv_iv_location);
            viewHolder.txt_date = convertView.findViewById(R.id.cv_iv_date);

            result = convertView;
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
            result = convertView;
        }

        // set values
        viewHolder.txt_controlCenter.setText(currenOperation.getControllcenter());
        viewHolder.txt_alarmType.setText(String.valueOf(currenOperation.getAlarmlevel()));
        viewHolder.txt_location.setText(currenOperation.getLocation().getStreet() + ", " + currenOperation.getLocation().getHousenumber());
        viewHolder.txt_date.setText(strDate + " - " + currenOperation.getDatetime());
        return convertView;
    }
}
