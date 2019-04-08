package com.example.cora.elikad_alarmierungsapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cora.elikad_alarmierungsapp.Data.AsyncTaskHandler;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncWebserviceTask;
import com.example.cora.elikad_alarmierungsapp.Data.LVAdapter;
import com.example.cora.elikad_alarmierungsapp.Data.Location;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.Data.Report;
import com.example.cora.elikad_alarmierungsapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AllAlarmsFragment extends Fragment implements AsyncTaskHandler{
    private OnFragmentInteractionListener mListener;

    static List<Operation> operations;
    ListView lv;
    View view;
    AsyncWebserviceTask task = null;
    private SharedPreferences preferences;
    private Gson gson;
    private Report r = null;
    private Context mContext;
    private Activity mActivity;
    Bundle bundle = new Bundle();


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
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        gson = new Gson();

        lv = (ListView) view.findViewById(R.id.rv_list);

        lv.setAdapter(new LVAdapter(this.getContext(), operations));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Operation o = operations.get(position);
                Location l = new Location("01", "Musterstraße", "9500", "Villach", "Kärnten");

                Context context = view.getContext();

                preferences.edit().putInt("currentOpId", o.getId());

                System.out.println("Test Opid" + o.getId());
                System.out.println("Test currentOpId: " + preferences.getInt("currentOpId", 1));

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy - mm:hh");

                bundle.putInt("id", o.getId());
                bundle.putString("center", o.getControlcenterName());
                bundle.putString("controllcenter", "Sirenenalarm für " + o.getControlcenterName());
                //bundle.putString("einsatzart", "EINSATZART: "+ o.getOperationType().toString() +  "(B " +o.getAlarmlevel() + ")");
                bundle.putString("einsatzart", "EINSATZART: "+ "Brandeinsatz" +  "(B " +o.getAlarmlevel() + ")");
                bundle.putString("bemerkung", "BEMERKUNG: " + o.getText());
                bundle.putString("street", "STRASSE: " +  l.getStreet() + ", " + l.getHousenumber());
                bundle.putString("ort", "ORT: " + l.getPostalcode() + ", " + l.getVillage());
                bundle.putString("caller", "CALLER: " + o.getCaller());
                bundle.putString("startDate", "START TIME: " + sdf.format(o.getStartDatetime()));
                bundle.putString("endDate", "END TIME: " + sdf.format(o.getEndDatetime()));


                try {
                    System.out.println("Test REport URL pref: " + preferences.getInt("currentOpId", 0));
                    task = new AsyncWebserviceTask("GET", "reports/" + o.getId(), AllAlarmsFragment.this, mContext);
                    System.out.println("Test REport URL: " + task.getUrl());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                task.execute();


                try {
                    /*AlarmDetailsFragment adf = AlarmDetailsFragment.newInstance();
                    System.out.println("TEst R in AllAlarms: " + r);
                    //Fragment fragment = (Fragment) (AlarmDetailsFragment.class).newInstance();
                    adf.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, adf).commit();*/
                } catch (Exception e) {
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

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccess(int statusCode, String content) {
        System.out.println("Test statusCode:" + statusCode);
        switch (statusCode) {
            case 200:
                Type lType = new TypeToken<ArrayList<Report>>() {}.getType();
                List<Report> r = gson.fromJson(content, lType);

                AlarmDetailsFragment adf = AlarmDetailsFragment.newInstance();
                System.out.println("TEst R in AllAlarms: " + r);
                //Fragment fragment = (Fragment) (AlarmDetailsFragment.class).newInstance();
                adf.setArguments(bundle);
                if(r.size() == 0)
                    adf.setCurrentR(null);
                else
                    adf.setCurrentR(r.get(0));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, adf).commit();
                //r = gson.fromJson(content, Report.class);
                System.out.println("Test report" + r);
                break;
            case 403:
                Toast.makeText(getActivity(), "Wrong inputs", Toast.LENGTH_SHORT).show();
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
        mActivity = activity;
    }
}
