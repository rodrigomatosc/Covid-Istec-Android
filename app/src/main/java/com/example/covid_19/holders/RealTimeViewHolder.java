package com.example.covid_19.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;

public class RealTimeViewHolder extends RecyclerView.ViewHolder {
    private TextView textConfirmed;
    private TextView textRecovered;
    private TextView textDeaths;
    private TextView textPopulation;
    private TextView textLife;
    private TextView textLatLong;
    private TextView textCountry;
    private TextView textViewCapital;
    private ImageView imageViewCountry;
    private TextView textViewContinent;
    private TextView textViewLastUpdate;

    public TextView getTextViewLastUpdate() {
        return textViewLastUpdate;
    }

    public TextView getTextViewContinent() {
        return textViewContinent;
    }

    public TextView getTextViewCapital() {
        return textViewCapital;
    }

    public ImageView getImageViewCountry() {
        return imageViewCountry;
    }


    public TextView getTextConfirmed() {
        return textConfirmed;
    }

    public TextView getTextRecovered() {
        return textRecovered;
    }

    public TextView getTextDeaths() {
        return textDeaths;
    }

    public TextView getTextPopulation() {
        return textPopulation;
    }

    public TextView getTextLife() {
        return textLife;
    }

    public TextView getTextLatLong() {
        return textLatLong;
    }

    public TextView getTextCountry() {
        return textCountry;
    }

    public RealTimeViewHolder(@NonNull View itemView) {
        super(itemView);

        textConfirmed = itemView.findViewById(R.id.textViewConfirmed);
        textRecovered = itemView.findViewById(R.id.textViewRecovered);
        textDeaths = itemView.findViewById(R.id.textViewDeaths);
        textPopulation = itemView.findViewById(R.id.textViewPopulation);
        textLife = itemView.findViewById(R.id.textViewLifeExp);
        textLatLong = itemView.findViewById(R.id.textViewLatLong);
        textCountry = itemView.findViewById(R.id.textViewCountryName);
        imageViewCountry = itemView.findViewById(R.id.imageView);
        textViewCapital = itemView.findViewById(R.id.textViewCapital);
        textViewContinent = itemView.findViewById(R.id.textViewContinet);
        textViewLastUpdate = itemView.findViewById(R.id.textViewLastUpdate);
    }
}
