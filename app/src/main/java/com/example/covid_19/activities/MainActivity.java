package com.example.covid_19.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.covid_19.R;
import com.example.covid_19.callbacks.APICallback;
import com.example.covid_19.fragments.History;
import com.example.covid_19.fragments.RealTime;
import com.example.covid_19.models.dtos.Country;
import com.example.covid_19.tasks.APITask;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNTRY = "COUNTRY";
    private static final String URL_COUNTRIES = "https://restcountries.eu/rest/v2/all";
    private APITask task = null;
    private Spinner spinner;
    private Country[] countries;
    private Country countrySelected = null;
    private int positionTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSpinner();
        initTabLayout();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    private <T extends Fragment> void initFragmentBase(T fragment){

        if(fragment== null) return;

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_COUNTRY, countrySelected);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void switchFragmentes(){

        switch (positionTab){
            case 0 : initFragmentBase(RealTime.newInstance());
                break;
            case 1 : initFragmentBase(History.newInstance());
                break;
        }
    }

    private void initTabLayout(){

        initFragmentBase(RealTime.newInstance());
        TabLayout tabLayout = findViewById(R.id.tabType);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                positionTab = tab.getPosition();
                switchFragmentes();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initSpinner(){

        ArrayList<String> countryArrayList = new ArrayList<>();
        countryArrayList.add("Selecione uma região");

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                countryArrayList));


        if (countries != null && countries.length > 0 ) return;

        APICallback callback = new APICallback() {
            @Override
            public void finishRequest(String result) {
                countries = new Gson().fromJson(result, Country[].class);
                ArrayList<String> countryArrayList = new ArrayList<>();
                countryArrayList.add("Selecione uma região");
                for (Country country : countries){
                    countryArrayList.add(country.getName());
                }

                spinner = findViewById(R.id.spinner);
                spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        countryArrayList));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        countrySelected = null;
                        int realPosition = --position;
                        if(realPosition < 0) return;
                        countrySelected = countries[realPosition];
                        switchFragmentes();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        };

        task = new APITask(callback);
        try {
            task.execute(new URL(URL_COUNTRIES));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {

        if(task != null){
            task.cancel(true);
        }

        super.onDestroy();
    }
}