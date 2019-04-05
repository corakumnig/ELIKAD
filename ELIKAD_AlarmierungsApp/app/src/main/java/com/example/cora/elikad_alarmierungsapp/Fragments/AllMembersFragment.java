package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cora.elikad_alarmierungsapp.Data.LVAdapter;
import com.example.cora.elikad_alarmierungsapp.Data.LVAdapterMembers;
import com.example.cora.elikad_alarmierungsapp.Data.Location;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.R;

import java.util.List;

public class AllMembersFragment extends Fragment {
    private AllMembersFragment.OnFragmentInteractionListener mListener;

    static List<Member> members;
    ListView lv;
    View view;
    boolean isFABOpen;
    FloatingActionButton fab1, fab2;

    public AllMembersFragment() {}

    public static AllAlarmsFragment newInstance() {
        AllAlarmsFragment fragment = new AllAlarmsFragment();
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

        /*FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fabAdd);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fabDelete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });*/

        lv = (ListView) view.findViewById(R.id.lv_allMembers);
        lv.setAdapter(new LVAdapterMembers(this.getContext(), members));

        System.out.println("TEST Member Size" + members.size());


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return view;
    }

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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
    }
}
