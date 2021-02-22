package com.example.covid_19.adapters;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.holders.RealTimeViewHolder;
import com.example.covid_19.models.dtos.InformationCovid;
import com.example.covid_19.tasks.SvgTask;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class RealTimeItemAdapter extends RecyclerView.Adapter {

    private ArrayList<InformationCovid> informations;
    private Context context;
    final String patternFormat = "#,###";
    DecimalFormat decimalFormat;

    public RealTimeItemAdapter(ArrayList<InformationCovid> informations, Context context) {
        this.informations = informations;
        this.context = context;
        decimalFormat = new DecimalFormat(patternFormat);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.real_time_card_item, parent, false);
        RealTimeViewHolder realTimeViewHolder = new RealTimeViewHolder(view);
        return realTimeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RealTimeViewHolder realTimeViewHolder = (RealTimeViewHolder) holder;
        InformationCovid informationCovid = informations.get(position);
        startCountAnimation(realTimeViewHolder.getTextConfirmed(), informationCovid.getConfirmed());
        startCountAnimation(realTimeViewHolder.getTextRecovered(), informationCovid.getRecovered());
        startCountAnimation(realTimeViewHolder.getTextDeaths(), informationCovid.getDeaths());
        realTimeViewHolder.getTextCountry().setText(informationCovid.getCountry());
        if (!informationCovid.getPopulation().isEmpty())
            realTimeViewHolder.getTextPopulation().setText(decimalFormat.format(Float.parseFloat(informationCovid.getPopulation())));
        realTimeViewHolder.getTextLatLong().setText(informationCovid.toStringLatLong());
        realTimeViewHolder.getTextLife().setText(informationCovid.getLifeExpectancy());
        realTimeViewHolder.getTextViewCapital().setText(informationCovid.getCapitalCity());
        realTimeViewHolder.getTextViewContinent().setText(informationCovid.getContinent());
        realTimeViewHolder.getTextViewLastUpdate().setText(informationCovid.getUpdated());

        SvgTask.fetchSvg(context, informationCovid.getCountryObject().getFlag(),realTimeViewHolder.getImageViewCountry());

        //imageTask.execute("https://restcountries.eu/data/aia.svg");

    }

    private void startCountAnimation(TextView textView, int number) {
        ValueAnimator animator = ValueAnimator.ofInt(0, number);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(decimalFormat.format(animation.getAnimatedValue()));
            }
        });
        animator.start();
    }
    @Override
    public int getItemCount() {
        return informations.size();
    }
}
