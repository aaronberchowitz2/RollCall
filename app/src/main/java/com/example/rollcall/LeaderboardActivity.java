package com.example.rollcall;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        String points = (String)extras.get("points");





        RecyclerView recycler = findViewById(R.id.org_recycler);
        recycler.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recycler.setLayoutManager(manager);

        adapter = new LeadersAdapter(leaders);
        recycler.setAdapter(adapter);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        newContactResult(data);
                    }
                });

        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), addLeader.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                                btn,
                                "bg"
                        );
                activityResultLauncher.launch(i, options);
            }
        });
    }

    protected void newContactResult (Intent data) {
        Bundle b = data.getExtras();
        Leaders leader = (Leaders) b.getSerializable("leader");
        adapter.AddContact(leader);
    }
}
