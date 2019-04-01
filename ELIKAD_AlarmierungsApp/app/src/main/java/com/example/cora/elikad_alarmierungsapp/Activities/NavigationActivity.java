package com.example.cora.elikad_alarmierungsapp.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cora.elikad_alarmierungsapp.Data.AsyncTaskHandler;
import com.example.cora.elikad_alarmierungsapp.Data.AsyncWebserviceTask;
import com.example.cora.elikad_alarmierungsapp.Data.Member;
import com.example.cora.elikad_alarmierungsapp.Data.Operation;
import com.example.cora.elikad_alarmierungsapp.Fragments.AllAlarmsFragment;
import com.example.cora.elikad_alarmierungsapp.Fragments.AllMembersFragment;
import com.example.cora.elikad_alarmierungsapp.Fragments.CreateReportFragment;
import com.example.cora.elikad_alarmierungsapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncTaskHandler {

    private ProgressDialog progDialog;
    private Gson gson;
    private SharedPreferences preferences;
    Fragment fragment = null;
    Class fragmentClass = null;
    private int caseNr = 0;
    AsyncWebserviceTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_allAlarms);

        View hView = navigationView.getHeaderView(0);
        TextView memberName = (TextView) hView.findViewById(R.id.nav_header_name);
        memberName.setText(preferences.getString("MemberFirstName", null) + " " + preferences.getString("MemberLastName", null));

        try {
            caseNr = 1;
            task = new AsyncWebserviceTask("POST", "members/" + preferences.getInt("MemberId", 0) + "/operations", NavigationActivity.this, getApplicationContext());
            task.execute();
            fragmentClass = AllAlarmsFragment.class;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        caseNr = 0;
        //MenuItem createReport = (MenuItem)findViewById(R.id.nav_createReport);
        //MenuItem showMembers = (MenuItem)findViewById(R.id.nav_showMembers);
        //createReport.setVisible(true);
        //showMembers.setVisible(true);
        /*createReport.setVisible(false);
        showMembers.setVisible(false);


        if(preferences.getString("MemberFunction", null).equals("Einsatzleiter")){
            createReport.setVisible(true);
        }else if(preferences.getString("MemberFunction", null).equals("Kommandant")){
            createReport.setVisible(true);
            showMembers.setVisible(true);
        }*/

        try {
            switch (id) {
                case R.id.nav_allAlarms:
                    caseNr = 1;
                    task = new AsyncWebserviceTask("POST", "members/" + preferences.getInt("MemberId", 0) + "/operations", NavigationActivity.this, getApplicationContext());
                    task.execute();
                    setTitle(item.getTitle());
                    fragmentClass = AllAlarmsFragment.class;
                    break;
                case R.id.nav_setSound:
                    caseNr = 2;
                    //ToDO
                    break;
                case R.id.nav_changeTel:
                    caseNr = 3;
                    changePhoneDialog();
                    break;
                case R.id.nav_logout:
                    caseNr = 4;
                    logoutDialog();
                    break;
                case R.id.nav_createReport:
                    caseNr = 5;
                    fragmentClass = CreateReportFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    displaySelectedFragment(fragment);
                    break;
                case R.id.nav_showMembers:
                    caseNr = 6;
                    task = new AsyncWebserviceTask("POST", "departments/" + preferences.getInt("MemberIdDepartment", 0) + "/members", NavigationActivity.this, getApplicationContext());
                    System.out.println("Test ID: " + preferences.getInt("MemberIdDepartment", 255));
                    task.execute();
                    setTitle(item.getTitle());
                    fragmentClass = AllMembersFragment.class;
                    break;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

        item.setChecked(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onPreExecute() {
        progDialog = new ProgressDialog(NavigationActivity.this);
        progDialog.setMessage("Loading ...");
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(true);
        progDialog.show();
    }

    @Override
    public void onSuccess(int statusCode, String content) {
        switch (statusCode) {
            case 200:
                switch(caseNr) {
                    case 1:
                        Type listType = new TypeToken<ArrayList<Operation>>() {
                        }.getType();
                        List<Operation> op = gson.fromJson(content, listType);
                        System.out.println("Test 3" + op);

                        AllAlarmsFragment.setOperations(op);
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        displaySelectedFragment(fragment);
                        break;
                    case 2:
                        //ToDO Sound
                        break;
                    case 3:
                        Toast.makeText(this, "Successfully changed", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
                        AsyncWebserviceTask.setAccessToken(null);
                        break;
                    case 5:
                        break;
                    case 6:
                        Type lType = new TypeToken<ArrayList<Member>>() {
                        }.getType();
                        List<Member> m = gson.fromJson(content, lType);

                        System.out.println("Test size: " + m.size());
                        System.out.println("Test member: " + m.toString());
                        AllMembersFragment.setMembers(m);
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        displaySelectedFragment(fragment);
                        break;
                }
                break;
            case 403:
                Toast.makeText(this, "Wrong inputs", Toast.LENGTH_SHORT).show();
                break;

            case 404:
                Toast.makeText(this, "Could't connect", Toast.LENGTH_SHORT).show();
                System.out.println("Test 7: " + caseNr);
                System.out.println("Test 9: " + task.getUrl() + " ... " + task.getHttpMethod());
                break;

            default:
                Toast.makeText(this, "Error: Try again.", Toast.LENGTH_SHORT).show();
                System.out.println("Test 10: " + statusCode);
        }

        progDialog.dismiss();
    }

    @Override
    public void onError(Error err) {
        progDialog.cancel();
        //Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        err.printStackTrace();
    }


    public void logoutDialog(){
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Logout");
        builderSingle.setMessage("Sind Sie sicher, dass Sie sich ausloggen wollen?");

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builderSingle.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //AsyncWebserviceTask task = null;
                        try {
                            task = new AsyncWebserviceTask("DELETE", "login/member", NavigationActivity.this, getApplicationContext());
                            System.out.println("Test 6: " + task.getUrl());
                            System.out.println("Test 8: " + task.getHttpMethod());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        task.execute();
                    }
                });

        builderSingle.show();
    }

    public void changePhoneDialog(){
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builderSingle.setTitle("Telefonnummer ändern");

        View dialogView = inflater.inflate(R.layout.dialog_changephone, null);
        builderSingle.setView(dialogView);

        final EditText txt_newPhone = (EditText) dialogView.findViewById(R.id.txt_newPhone);

        System.out.println("Test 11: " + txt_newPhone);

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builderSingle.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences.edit().putString("MemberPhonenumber", txt_newPhone.getText().toString()).commit();

                        int id = preferences.getInt("MemberId", 0);
                        String fName = preferences.getString("MemberFirstName", null);
                        String lName = preferences.getString("MemberLastName", null);
                        String email = preferences.getString("MemberEmail", null);
                        String phone = preferences.getString("MemberPhonenumber", null);
                        String function = preferences.getString("MemberFunction", null);
                        int idDepartment = preferences.getInt("MemberIdDepartment", 0);
                        String birthDate = preferences.getString("MemberBirthDate", null);
                        String entryDate = preferences.getString("MemberEntryDate", null);


                        Member member  = new Member(id, fName, lName, email, phone, function, idDepartment, birthDate, entryDate);
                        System.out.println("Test 12: " + member.toString());

                        try {
                            String json = gson.toJson(member);
                            task = new AsyncWebserviceTask("PUT", "members/" + preferences.getInt("MemberId", 0) , NavigationActivity.this, getApplicationContext());
                            task.execute(null, json);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });

        builderSingle.show();
    }
}
