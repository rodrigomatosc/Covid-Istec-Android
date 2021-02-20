package com.example.covid_19.fragments;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.covid_19.R;
import com.example.covid_19.adapters.RealTimeItemAdapter;
import com.example.covid_19.callbacks.APICallback;
import com.example.covid_19.models.dtos.Country;
import com.example.covid_19.models.dtos.InformationCovid;
import com.example.covid_19.tasks.APITask;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class RealTime extends Fragment {

    private static final String KEY_COUNTRY = "COUNTRY";
    private static final String URL_INFORMATON = "https://covid-api.mmediagroup.fr/v1/cases?country=";

    APITask task = null;
    private Country country;
    private View view = null;

    public RealTime() {
    }

    public static RealTime newInstance() {
        RealTime fragment = new RealTime();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        country = (Country) getArguments().getSerializable(KEY_COUNTRY);
        initInformations();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_real_time, container, false);
        return view;
    }

    private void initInformations(){
        APICallback callback = new APICallback() {
            @Override
            public void finishRequest(String result) {

                Gson gson = new Gson();
                JsonObject object = gson.fromJson( result, JsonObject.class);
                JsonElement jsonElement = object.get("All");

                if(jsonElement == null){
                    Toast.makeText(getContext(), country.getName() + " não possuí dados de COVID-19",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String string = jsonElement.toString();
                InformationCovid informationCovid = gson.fromJson(string, InformationCovid.class);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

                informationCovid.setCountryObject(country);
                ArrayList<InformationCovid> arrayList = new ArrayList<>();
                arrayList.add(informationCovid);

                RealTimeItemAdapter realTimeItemAdapter = new RealTimeItemAdapter(arrayList, getContext());

                RecyclerView recycle = view.findViewById(R.id.recycleViewRealTime);
                recycle.setLayoutManager(layoutManager);
                recycle.setAdapter(realTimeItemAdapter);
            }
        };

        task = new APITask(callback);
        try {
            if(country != null){
                task.execute(new URL(URL_INFORMATON + country.getName()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}