package com.example.rajick.elikad_einsatzleitmonitor.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rajick.elikad_einsatzleitmonitor.Data.Member;
import com.example.rajick.elikad_einsatzleitmonitor.Data.Operation;
import com.example.rajick.elikad_einsatzleitmonitor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
author: Kristian Rajic
 */


public class AcceptedAdapter extends BaseAdapter {
    private ArrayList<Member> members;
    private LayoutInflater inflater;

    public AcceptedAdapter(Context context, ArrayList<Member> members) {
        this.members = members;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            // get current veranstaltung
            Member currentMember = (Member) getItem(position);

            // UI references
            convertView = inflater.inflate(R.layout.accepted_list_layout, parent, false);
            TextView txt_name = convertView.findViewById(R.id.txt_Name);
            TextView txt_acceptCount = convertView.findViewById(R.id.txt_acceptCount);
            TextView txt_ELCount = convertView.findViewById(R.id.txt_ELCount);
            TextView txt_ASCount = convertView.findViewById(R.id.txt_ASCount);
            TextView txt_MACount = convertView.findViewById(R.id.txt_MACount);
            TextView txt_SACount = convertView.findViewById(R.id.txt_SACount);
            //ToDo: Counts outside, because only ListVariables are accessable
            // set values
            txt_name.setText(currentMember.getFirstName() +" "+ currentMember.getLastName());

        }
        return convertView;
    }
}
