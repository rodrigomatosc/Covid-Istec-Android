package com.example.covid_19.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.holders.HistoryViewHolder;
import com.example.covid_19.models.dtos.InformationCovid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HistoryItemAdapter extends RecyclerView.Adapter {

    List<Map.Entry<Date, String>>  mapItens;
    private Context context;
    String typeHistory;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


    public HistoryItemAdapter(List<Map.Entry<Date, String>> mapItens, Context context, String typeHistory) {
        this.mapItens = mapItens;
        this.context = context;
        this.typeHistory = typeHistory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.history_card_item, parent, false);
        HistoryViewHolder historyViewHolder = new HistoryViewHolder(view);
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Map.Entry<Date, String> entry = mapItens.get(position);
        Date key = entry.getKey();
        String value = entry.getValue();;

        String dateString = format.format(key);

        HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
        historyViewHolder.getTextViewDate().setText(dateString);
        historyViewHolder.getTextViewNumber().setText(value);
        historyViewHolder.getTextViewType().setText(typeHistory);
    }

    @Override
    public int getItemCount() {
        return mapItens.size();
    }
}
