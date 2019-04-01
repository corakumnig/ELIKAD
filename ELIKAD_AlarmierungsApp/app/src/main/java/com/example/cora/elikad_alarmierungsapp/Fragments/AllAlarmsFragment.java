package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cora.elikad_alarmierungsapp.Data.LVAdapter;
import com.example.cora.elikad_alarmierungsapp.Data.Location;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.R;

import java.util.List;

public class AllAlarmsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    static List<Operation> operations;
    ListView lv;
    View view;

    public AllAlarmsFragment() {}

    public static AllAlarmsFragment newInstance() {
        AllAlarmsFragment fragment = new AllAlarmsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public static void setOperations (List<Operation> ops){
        operations = ops;
    }
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_alarms, container, false);
        getActivity().setTitle("All Alarms");

        lv = (ListView) view.findViewById(R.id.rv_list);
        //lv.setLayoutManager(new LinearLayoutManager(getContext()));

        lv.setAdapter(new LVAdapter(this.getContext(), operations));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Operation o = operations.get(position);
                Location l = new Location("01", "Musterstraße", "9500", "Villach", "Kärnten");

                Context context = view.getContext();

                System.out.println("Test 13: " + o.toString());
                System.out.println("Test 14: " + position);
                System.out.println("Test 14: " + operations.size());


                Bundle bundle = new Bundle();
                bundle.putString("center", o.getControlcenterName());
                bundle.putString("controllcenter", "Sirenenalarm für " + o.getControlcenterName());
                //bundle.putString("einsatzart", "EINSATZART: " + o.getOperationType().toString() + ", (B " +o.getAlarmlevel() + ")");
                bundle.putString("einsatzart", "EINSATZART: Brandeinsatz, (B " +o.getAlarmlevel() + ")");
                bundle.putString("bemerkung", "BEMERKUNG: " + o.getText());
                bundle.putString("street", "STRASSE: " +  l.getStreet() + ", " + l.getHousenumber());
                bundle.putString("ort", "ORT: " + l.getPostalcode() + ", " + l.getVillage());

                try {
                    Fragment fragment = (Fragment) (AlarmDetailsFragment.class).newInstance();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
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
}
