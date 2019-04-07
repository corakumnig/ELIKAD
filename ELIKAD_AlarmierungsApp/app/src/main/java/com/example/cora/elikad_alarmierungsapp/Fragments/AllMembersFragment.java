package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cora.elikad_alarmierungsapp.Activities.LoginActivity;
import com.example.cora.elikad_alarmierungsapp.Activities.NavigationActivity;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncTaskHandler;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncWebserviceTask;
import com.example.cora.elikad_alarmierungsapp.Data.LVAdapter;
import com.example.cora.elikad_alarmierungsapp.Data.LVAdapterMembers;
import com.example.cora.elikad_alarmierungsapp.Data.Location;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllMembersFragment extends Fragment implements AsyncTaskHandler {
    private AllMembersFragment.OnFragmentInteractionListener mListener;

    static List<Member> members;
    private static ListView lv;
    private static View view;
    private static int caseNr;
    private static AsyncWebserviceTask task = null;
    private SharedPreferences preferences;
    private Gson gson;
    private static Context mContext;
    private static FragmentActivity mActivity;
    private FragmentManager fm;

    public FragmentManager getFm() {
        return fm;
    }

    public AllMembersFragment() {
    }

    public static AllMembersFragment newInstance() {
        AllMembersFragment fragment = new AllMembersFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public static void setMembers (List<Member> mem){
        members = mem;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_members, container, false);
        getActivity().setTitle("All Members");
        fm = getFragmentManager();
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMemberDialogFragment dialogFragment = NewMemberDialogFragment.newInstance(null);
                dialogFragment.show(fm, "New Member");
            }
        });

        setListView(AllMembersFragment.this);

        return view;
    }

    public static void setListView(final AsyncTaskHandler handler){
        final FragmentManager fm = mActivity.getSupportFragmentManager();
        System.out.println("Test FragmentManager: " + fm);
        lv = (ListView) view.findViewById(R.id.lv_allMembers);
        lv.setAdapter(new LVAdapterMembers(mContext, members));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Member m = members.get(position);
                PopupMenu popup = new PopupMenu(mContext, view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.popup_delete:
                                Toast.makeText(mActivity,"Delete pressed", Toast.LENGTH_SHORT).show();
                                try {
                                    caseNr = 1;
                                    task = new AsyncWebserviceTask("DELETE", "members/" + m.getId(), handler, mContext);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                task.execute();
                                break;
                            case R.id.popup_edit:
                                Toast.makeText(mActivity,"Edit pressed", Toast.LENGTH_SHORT).show();
                                NewMemberDialogFragment dialogFragment = NewMemberDialogFragment.newInstance(m);
                                dialogFragment.show(fm, "New Member");
                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    private void showDialog(){}

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPreExecute() {

    }

    public void refreshList(){
        caseNr = 2;
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        try {
            task = new AsyncWebserviceTask("POST", "departments/" + preferences.getInt("MemberIdDepartment", 4) + "/members", AllMembersFragment.this, mContext);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        task.execute();
    }

    @Override
    public void onSuccess(int statusCode, String content) {
        switch (statusCode) {
            case 200:
                switch(caseNr){
                    case 1:
                        refreshList();
                        break;
                    case 2:
                        gson = new Gson();
                        Fragment fragment = null;
                        AsyncTaskHandler ath = AllMembersFragment.newInstance();
                        Type lType = new TypeToken<ArrayList<Member>>() {}.getType();
                        List<Member> m = gson.fromJson(content, lType);
                        AllMembersFragment.setMembers(m);
                        AllMembersFragment.setListView(ath);
                        break;
                }
                break;
            case 403:
                Toast.makeText(getActivity(), "Wrong inputs", Toast.LENGTH_SHORT).show();
                break;

            case 404:
                Toast.makeText(getActivity(), "Could't connect", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getActivity(), "Error: Try again.", Toast.LENGTH_SHORT).show();
                System.out.println("Test 10: " + statusCode);
        }
    }

    @Override
    public void onError(Error err) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
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
        mActivity = (FragmentActivity) activity;
    }
}
