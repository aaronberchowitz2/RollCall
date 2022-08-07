package com.example.rollcall;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class LeadersViewHolder extends RecyclerView.ViewHolder {
    public CardView card;
    public ImageView avatar;
    public TextView name;
    public TextView points;


    public LeadersViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.card);
        avatar = itemView.findViewById(R.id.avatar);
        name = itemView.findViewById(R.id.name);
        points = itemView.findViewById(R.id.points);


    }
}