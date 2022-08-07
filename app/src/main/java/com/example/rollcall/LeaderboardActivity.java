package com.example.rollcall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    LeadersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        DataPersistencyHelper.Context = getApplicationContext();
        List<Leaders> leaders = DataPersistencyHelper.LoadData();
        Bundle extras = getIntent().getExtras();
        Leaders points = (Leaders) extras.getSerializable("points");




        RecyclerView recycler = findViewById(R.id.org_recycler);
        recycler.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recycler.setLayoutManager(manager);

        adapter = new LeadersAdapter(leaders);
        recycler.setAdapter(adapter);
    }
}