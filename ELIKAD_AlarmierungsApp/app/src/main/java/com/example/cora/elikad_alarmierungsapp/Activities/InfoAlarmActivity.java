package com.example.cora.elikad_alarmierungsapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cora.elikad_alarmierungsapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class InfoAlarmActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView tv_controllCenter, txt_alarm, txt_operationType, txt_info, txt_street, txt_ort;
    ImageView iv_alarm;

    private MapView mapView;
    private GoogleMap gmap;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alarm);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        initComponents(bundle);

        initGoogleMap(savedInstanceState);
    }

    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    private void initComponents(Bundle bundle){
        tv_controllCenter = (TextView)findViewById(R.id.tv_controllCenter);
        txt_alarm = (TextView)findViewById(R.id.txt_alarm);
        txt_operationType = (TextView)findViewById(R.id.txt_operationType);
        txt_info = (TextView)findViewById(R.id.txt_info);
        txt_street = (TextView)findViewById(R.id.txt_street);
        txt_ort = (TextView)findViewById(R.id.txt_ort);
        iv_alarm = (ImageView)findViewById(R.id.iv_alarm);
        mapView = (MapView)findViewById(R.id.mapView);

        tv_controllCenter.setText(bundle.getString("center"));
        txt_alarm.setText(bundle.getString("controllcenter"));
        txt_operationType.setText(bundle.getString("einsatzart"));
        txt_info.setText(bundle.getString("bemerkung"));
        txt_street.setText(bundle.getString("street"));
        txt_ort.setText(bundle.getString("ort"));
        iv_alarm.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }
}
