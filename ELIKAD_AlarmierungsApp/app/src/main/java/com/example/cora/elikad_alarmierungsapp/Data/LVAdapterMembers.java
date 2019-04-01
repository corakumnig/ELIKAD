package com.example.cora.elikad_alarmierungsapp.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cora.elikad_alarmierungsapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LVAdapterMembers extends BaseAdapter {
    static List<Member> members;
    Context context;
    private static LayoutInflater inflater = null;

    public LVAdapterMembers(Context context, List<Member> member){
        this.members = member;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View vi = convertView;

        if(vi == null)
            vi = inflater.inflate(R.layout.adapter_member, null);

        TextView adMember_nachname = vi.findViewById(R.id.adMember_nachname);
        TextView adMember_vorname = vi.findViewById(R.id.adMember_vorname);
        TextView adMember_telNr = vi.findViewById(R.id.adMember_telNr);
        TextView adMember_email = vi.findViewById(R.id.adMember_email);
        TextView adMember_birthDate = vi.findViewById(R.id.adMember_birthDate);
        TextView adMember_entryDate = vi.findViewById(R.id.adMember_entryDate);


        Member m = members.get(position);
        adMember_nachname.setText(m.getLastname());
        adMember_vorname.setText(m.getFirstname());
        adMember_telNr.setText(m.getPhonenumber());
        adMember_email.setText(m.getEmail());
        adMember_birthDate.setText(m.getDateOfBirth());
        adMember_entryDate.setText(m.getDateOfEntry());

        return vi;
    }
}
