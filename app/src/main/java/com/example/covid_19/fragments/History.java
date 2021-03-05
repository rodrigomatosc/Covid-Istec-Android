package com.example.covid_19.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.R;
import com.example.covid_19.adapters.HistoryItemAdapter;
import com.example.covid_19.adapters.RealTimeItemAdapter;
import com.example.covid_19.callbacks.APICallback;
import com.example.covid_19.models.dtos.Country;
import com.example.covid_19.models.dtos.InformationCovid;
import com.example.covid_19.tasks.APITask;
import com.example.covid_19.utils.MapValueKeyComparator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class History extends Fragment {

    private static final String KEY_COUNTRY = "COUNTRY";
    private static final String KEY_TYPE_HISTORY = "TYPE_HISTORY";

    private static final String URL_INFORMATON = "https://covid-api.mmediagroup.fr/v1/history?country=";

    APITask task = null;
    private Country country;
    private View view = null;
    String typeHistory;

    TextView textViewCapital ;
    TextView textViewContinent ;
    TextView textViewPopulation ;
    TextView textViewLife ;
    TextView textViewNameCountry;

    public History() {

    }

    public static History newInstance() {
        History fragment = new History();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        country = (Country) getArguments().getSerializable(KEY_COUNTRY);
        typeHistory = getArguments().getString(KEY_TYPE_HISTORY);
        searchInformations();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);
        initComponents(view);
        searchInformations();
        return view;
    }

    private void initComponents(View view){
        final String defaultValues = "----------";

        textViewCapital = view.findViewById(R.id.textViewCapitalHistory);
        textViewContinent = view.findViewById(R.id.textViewContinetHistory);
        textViewPopulation = view.findViewById(R.id.textViewPopulationHistory);
        textViewLife = view.findViewById(R.id.textViewLifeHistory);
        textViewNameCountry = view.findViewById(R.id.textViewCountryNameHistory);

        textViewCapital.setText(defaultValues);
        textViewContinent.setText(defaultValues);
        textViewPopulation.setText(defaultValues);
        textViewLife.setText(defaultValues);
        textViewNameCountry.setText("");
    }

    private void setValuesComponents(InformationCovid information){

        textViewCapital.setText(information.getCapitalCity());
        textViewContinent.setText(information.getContinent());
        textViewPopulation.setText(information.getPopulation());
        textViewLife.setText(information.getLifeExpectancy());
        textViewNameCountry.setText(information.getCountry());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

        HashMap<Date, String> hash = information.getDates();
        List<Map.Entry<Date, String>> list = new ArrayList<Map.Entry<Date, String>>(hash.entrySet());
        Collections.sort(list, new MapValueKeyComparator<Date, String>());

        HistoryItemAdapter historyItemAdapter = new HistoryItemAdapter(list, getContext(), information.getTypeHistory());
        RecyclerView recycle = view.findViewById(R.id.recycleViewHistory);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(historyItemAdapter);
    }

    private void searchInformations(){

        if(view == null || country == null) return;

        String typeString = typeHistory == "deaths" ? "Deaths:" : "Recovered:";

        APICallback callback = new APICallback() {
            @Override
            public void finishRequest(String result) {

                Gson gson = new Gson();
                JsonObject object = gson.fromJson( result, JsonObject.class);

                Set<String> keys = object.keySet();
                JsonElement jsonElement = object.get("All");

                if(jsonElement == null && keys == null || keys.isEmpty()){
                    Toast.makeText(getContext(), country.getName() + " não possuí dados de COVID-19",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<InformationCovid> arrayList = new ArrayList<>();

                if(jsonElement == null){
                    for(String key : keys){
                        JsonObject jsonObjectKey = object.getAsJsonObject(key);

                        if(jsonObjectKey == null ) continue;

                        JsonElement jsonElementKeyAll = jsonObjectKey.get("All");

                        if(jsonElementKeyAll == null) continue;

                        InformationCovid informationCovid = gson.fromJson(jsonElementKeyAll.toString(), InformationCovid.class);
                        informationCovid.setCountryObject(country);
                        informationCovid.setTypeHistory(typeString);
                        arrayList.add(informationCovid);
                    }
                }else{
                    String string = jsonElement.toString();
                    InformationCovid informationCovid = gson.fromJson(string, InformationCovid.class);
                    informationCovid.setCountryObject(country);
                    informationCovid.setTypeHistory(typeString);
                    arrayList.add(informationCovid);
                    setValuesComponents(informationCovid);
                }
            }
        };

        task = new APITask(callback);
        try {
            task.execute(new URL(URL_INFORMATON + country.getName()+"&status="+typeHistory));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}