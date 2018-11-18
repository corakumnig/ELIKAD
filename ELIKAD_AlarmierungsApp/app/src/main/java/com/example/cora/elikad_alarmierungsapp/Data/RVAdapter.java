package com.example.cora.elikad_alarmierungsapp.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cora.elikad_alarmierungsapp.Activities.InfoAlarmActivity;
import com.example.cora.elikad_alarmierungsapp.Activities.LoginActivity;
import com.example.cora.elikad_alarmierungsapp.Activities.NavigationActivity;
import com.example.cora.elikad_alarmierungsapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.OperationViewHolder> {
    public static class OperationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView iv_bell;
        TextView txt_controlCenter;
        TextView txt_alarmType;
        TextView txt_location;
        TextView txt_date;
        View v;

        OperationViewHolder(View itemView) {
            super(itemView);
            v  = itemView;
            cv = (CardView)itemView.findViewById(R.id.cv_operation);
            iv_bell = itemView.findViewById(R.id.cv_iv_bell);
            txt_controlCenter = itemView.findViewById(R.id.cv_tv_controllCenter);
            txt_alarmType = itemView.findViewById(R.id.cv_iv_alarmtyp);
            txt_location = itemView.findViewById(R.id.cv_iv_location);
            txt_date = itemView.findViewById(R.id.cv_iv_date);

            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Operation o = operations.get(pos);
                    Context context = v.getContext();


                    Intent intent = new Intent(v.getContext(), InfoAlarmActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("center", o.getControllcenter());
                    bundle.putString("controllcenter", "Sirenenalarm für " + o.getControllcenter());
                    bundle.putString("einsatzart", "EINSATZART: " + o.getOperationType().toString() + ", (B " +o.getAlarmlevel() + ")");
                    bundle.putString("bemerkung", "BEMERKUNG: " + o.getDescription());
                    bundle.putString("street", "STRASSE: " +  o.getLocation().getStreet() + ", " + o.getLocation().getHousenumber());
                    bundle.putString("ort", "ORT: " + o.getLocation().getPostalcode() + ", " + o.getLocation().getVillage());

                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }

    static List<Operation> operations;
    Context context;
    AdapterView.OnItemClickListener listener;

    public RVAdapter(List<Operation> operations, Context context){
        this.operations = operations;
        this.context = context;
    }

    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_operation, viewGroup, false);
        OperationViewHolder ovh = new OperationViewHolder(v);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OperationViewHolder operationViewHolder, int i) {
        Date datetime = operations.get(i).getDatetime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String strDate = dateFormatter.format(datetime);

        operationViewHolder.txt_controlCenter.setText(operations.get(i).getControllcenter());
        operationViewHolder.txt_alarmType.setText(operations.get(i).getOperationType() + ", B " +String.valueOf(operations.get(i).getAlarmlevel()));
        operationViewHolder.txt_location.setText(operations.get(i).getLocation().getStreet() + ", " + operations.get(i).getLocation().getHousenumber());
        operationViewHolder.txt_date.setText(strDate + " - " + operations.get(i).getDatetime());
        operationViewHolder.iv_bell.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    private Operation getCurrentOp(int i){
        return operations.get(i);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
