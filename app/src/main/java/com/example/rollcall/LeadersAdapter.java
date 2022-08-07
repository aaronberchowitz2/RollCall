package com.example.rollcall;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class LeadersAdapter  extends RecyclerView.Adapter<LeadersViewHolder> {
        private List<Leaders> Leaders;

        public LeadersAdapter (List<Leaders> leaders) {
                this.Leaders = leaders;
        }

        @NonNull
        @Override
        public LeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaders, parent, false);
                LeadersViewHolder vh = new LeadersViewHolder(v);
                return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull LeadersViewHolder holder, int position) {
                Leaders leaders = Leaders.get(position);

                holder.avatar.setImageResource(leaders.getImage());
                holder.name.setText(leaders.getName());
                holder.points.setText(leaders.getPoints());

        }
                public int getItemCount () {
                        return Leaders.size();
                }
        }
