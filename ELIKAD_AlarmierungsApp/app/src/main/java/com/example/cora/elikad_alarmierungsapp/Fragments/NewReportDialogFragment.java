package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cora.elikad_alarmierungsapp.Data.AsyncTaskHandler;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncWebserviceTask;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.Data.Report;
import com.example.cora.elikad_alarmierungsapp.R;
import com.google.gson.Gson;

import java.net.MalformedURLException;

public class NewReportDialogFragment extends DialogFragment implements AsyncTaskHandler {

    static AsyncWebserviceTask task = null;
    private SharedPreferences preferences;
    private Gson gson;
    private Report r;
    private Context mContext;
    private Activity mActivity;

    public void setR (Report r){
        this.r = r;
    }

    public static NewReportDialogFragment newInstance(){
        NewReportDialogFragment newReportDialog = new NewReportDialogFragment();
        return newReportDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        gson = new Gson();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_newbericht, null);
        builder.setView(dialogView);

        final Bundle args = getArguments();
        System.out.println("Test args: " + args);

        System.out.println("Test R" + r);

        if(r != null){
            ((TextView)dialogView.findViewById(R.id.op_einsatz)).setText(args.getString("controllcenter", null) + " operation: " + r.getOperationId());
            ((EditText)dialogView.findViewById(R.id.op_einsatzdauer)).setText(r.getOperationTime());
            ((EditText)dialogView.findViewById(R.id.op_reason)).setText(r.getOperationReason());
            ((EditText)dialogView.findViewById(R.id.op_object)).setText(r.getOperationObject());
            ((EditText)dialogView.findViewById(R.id.op_verl)).setText(Integer.toString(r.getOperationInjured()));
            ((EditText)dialogView.findViewById(R.id.op_get)).setText(Integer.toString(r.getOperationKilled()));
            ((EditText)dialogView.findViewById(R.id.op_sonst)).setText(r.getOperationOther());

        }else if (args != null){
            ((TextView)dialogView.findViewById(R.id.op_einsatz)).setText(args.getString("controllcenter", null) + " operation: " + args.getInt("opId", 0));
        }

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String jsonString = "";

                try {

                    String time = ((EditText)dialogView.findViewById(R.id.op_einsatzdauer)).getText().toString();
                    String reason = ((EditText)dialogView.findViewById(R.id.op_reason)).getText().toString();
                    String object = ((EditText)dialogView.findViewById(R.id.op_object)).getText().toString();
                    int verl = Integer.parseInt(((EditText)dialogView.findViewById(R.id.op_verl)).getText().toString());
                    int get = Integer.parseInt(((EditText)dialogView.findViewById(R.id.op_get)).getText().toString());
                    String sonst = ((EditText)dialogView.findViewById(R.id.op_sonst)).getText().toString();

                    int opId = args.getInt("opId", 0);
                    int depId = preferences.getInt("MemberIdDepartment",0);

                    Report r = new Report(opId, time, reason, object, verl, get, sonst, depId);

                    System.out.println("Test newReport: " + r);
                    jsonString = gson.toJson(r);

                    task = new AsyncWebserviceTask("POST", "/reports", NewReportDialogFragment.this, mContext);
                    task.execute(null, jsonString);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccess(int statusCode, String content) {
        System.out.println("Test statusCode New Report:" + statusCode);
        switch (statusCode) {
            case 200:
                Toast.makeText(mActivity, "Report added", Toast.LENGTH_SHORT).show();
                break;
            case 403:
                Toast.makeText(mActivity, "Wrong inputs", Toast.LENGTH_SHORT).show();
                break;

            case 404:
                Toast.makeText(mActivity, "Could't connect", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(mActivity, "Error: Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Error err) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
}
