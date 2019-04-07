package com.example.cora.elikad_alarmierungsapp.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.cora.elikad_alarmierungsapp.Activities.NavigationActivity;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncTaskHandler;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncWebserviceTask;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewMemberDialogFragment extends DialogFragment implements AsyncTaskHandler {

    private AsyncWebserviceTask task = null;
    private SharedPreferences preferences;
    private Gson gson;
    int caseNr;
    private Context mContext;
    private Activity mActivity;



    // private NewMemberDialogFragment.OnFragmentInteractionListener mListener;



    public static NewMemberDialogFragment newInstance(Member m){
        NewMemberDialogFragment newMemberDialog = new NewMemberDialogFragment();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (m != null) {

                Date birth = sdf.parse(m.getDateOfBirth());
                String birthDate = sdf.format(birth);

                Date entry = sdf.parse(m.getDateOfEntry());
                String entryDate = sdf.format(entry);

                Bundle bundle = new Bundle();
                bundle.putString("fn", m.getFirstname());
                bundle.putString("ln", m.getLastname());
                bundle.putString("email", m.getEmail());
                bundle.putString("phone", m.getPhonenumber());
                bundle.putString("birth", birthDate);
                bundle.putString("entry", entryDate);
                bundle.putString("svnr", m.getSvNr());
                bundle.putString("pin", m.getPin());
                bundle.putInt("id", m.getId());
                bundle.putString("gender", m.getGender());
                newMemberDialog.setArguments(bundle);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return newMemberDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        gson = new Gson();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_newmember, null);
        builder.setView(dialogView);

        final Bundle args = getArguments();

        if (args != null){
            caseNr = 1;
            ((EditText)dialogView.findViewById(R.id.newM_firstName)).setText(args.getString("fn", null));
            ((EditText)dialogView.findViewById(R.id.newM_lastName)).setText(args.getString("ln", null));
            if(args.getString("gender", null).equals("male")) {
                ((Spinner) dialogView.findViewById(R.id.newM_spinner)).setSelection(0);
            }else {
                ((Spinner) dialogView.findViewById(R.id.newM_spinner)).setSelection(1);
            }
            ((EditText)dialogView.findViewById(R.id.newM_email)).setText(args.getString("email", null));
            ((EditText)dialogView.findViewById(R.id.newM_phone)).setText(args.getString("phone", null));
            ((EditText)dialogView.findViewById(R.id.newM_birthDate)).setText(args.getString("birth", null));
            ((EditText)dialogView.findViewById(R.id.newM_entryDate)).setText(args.getString("entry", null));
            ((EditText)dialogView.findViewById(R.id.newM_svnr)).setText(args.getString("svnr", null));
            ((EditText)dialogView.findViewById(R.id.newM_pin)).setText(args.getString("pin", null));
        }
        else if(args == null){
            caseNr = 2;
        }

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String jsonString = "";

                System.out.println("Test casenr " +caseNr);

                try {
                    String fn = ((EditText)dialogView.findViewById(R.id.newM_firstName)).getText().toString();
                    String ln = ((EditText)dialogView.findViewById(R.id.newM_lastName)).getText().toString();
                    String email = ((EditText)dialogView.findViewById(R.id.newM_email)).getText().toString();
                    String phone = ((EditText)dialogView.findViewById(R.id.newM_phone)).getText().toString();
                    String bDate = ((EditText)dialogView.findViewById(R.id.newM_birthDate)).getText().toString();
                    String eDate = ((EditText)dialogView.findViewById(R.id.newM_entryDate)).getText().toString();
                    String sVNr = ((EditText)dialogView.findViewById(R.id.newM_svnr)).getText().toString();
                    String pin = ((EditText)dialogView.findViewById(R.id.newM_pin)).getText().toString();
                    String gender = ((Spinner)dialogView.findViewById(R.id.newM_spinner)).getSelectedItem().toString();

                    int idDept = preferences.getInt("MemberIdDepartment", 0);

                    Member m = new Member(fn, ln, email, phone, idDept, bDate, eDate, sVNr, pin, gender);
                    System.out.println("Test NewMember: " + m);
                    jsonString = gson.toJson(m);

                    if(caseNr == 1){
                        task = new AsyncWebserviceTask("PUT", "members/" + args.getInt("id"), NewMemberDialogFragment.this, mContext);
                        System.out.println("Task PUT: " + task.getUrl());
                        System.out.println("Task PUT Json " + jsonString);
                        task.execute(null, jsonString);
                    }else if(caseNr == 2) {
                        task = new AsyncWebserviceTask("POST", "members", NewMemberDialogFragment.this, mContext);
                        System.out.println("Task POST: " + task.getUrl());
                        System.out.println("Task Post Json " + jsonString);
                        task.execute(null, jsonString);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch(Exception ex){
                    ex.printStackTrace();
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

    public void refreshList(){
        caseNr = 3;
        try {
            System.out.println("TEST Error: " + mContext);
            task = new AsyncWebserviceTask("POST", "departments/" + preferences.getInt("MemberIdDepartment", 0) + "/members", NewMemberDialogFragment.this, mContext);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        task.execute();
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccess(int statusCode, String content) {
        System.out.println("Test statusCode:" + statusCode);
        switch (statusCode) {
            case 200:
                switch (caseNr){
                    case 1:
                        refreshList();
                        break;
                    case 2:
                        refreshList();
                        break;
                    case 3:
                        Fragment fragment = null;
                        AsyncTaskHandler ath = AllMembersFragment.newInstance();
                        Type lType = new TypeToken<ArrayList<Member>>() {}.getType();
                        List<Member> m = gson.fromJson(content, lType);

                        System.out.println("Test size: " + m.size());
                        System.out.println("Test memberlist: " + m.toString());
                        AllMembersFragment.setMembers(m);
                        AllMembersFragment.setListView(ath);
                        System.out.println("Test FM: " + ((AllMembersFragment) ath).getFm());
                        break;
                }
                break;
            case 403:
                System.out.println("Wrong inputs");
                //Toast.makeText(getActivity(), "Wrong inputs", Toast.LENGTH_SHORT).show();
                break;

            case 404:
                Toast.makeText(getActivity(), "Could't connect", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getActivity(), "Error: Try again.", Toast.LENGTH_SHORT).show();
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

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewMemberDialogFragment.OnFragmentInteractionListener) {
            mListener = (NewMemberDialogFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/
}
