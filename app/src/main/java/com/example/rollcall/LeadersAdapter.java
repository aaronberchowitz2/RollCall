package com.example.rollcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class LeadersAdapter  extends RecyclerView.Adapter<LeadersViewHolder> {
        private List<Leaders> Leaders;
        public LeadersAdapter(List<Leaders> Leaders)
        {
                this.Leaders = Leaders;
        }

        public void AddContact(Leaders leaders)
        {
                Leaders.add(leaders);
                notifyDataSetChanged();
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
                Leaders leader = Leaders.get(position);

                holder.avatar.setImageResource(leader.getImage());
                holder.name.setText(leader.getName());
                holder.points.setText(leader.getPoints());

        }
                public int getItemCount () {
                        return Leaders.size();
                }
        }
