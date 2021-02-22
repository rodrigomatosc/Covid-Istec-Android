package com.example.covid_19.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewDate;
    private TextView textViewType;
    private TextView textViewNumber;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewDate= itemView.findViewById(R.id.textViewDate);
        textViewNumber = itemView.findViewById(R.id.textViewNumber);
        textViewType = itemView.findViewById(R.id.textViewType);
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }

    public TextView getTextViewType() {
        return textViewType;
    }

    public TextView getTextViewNumber() {
        return textViewNumber;
    }
}
