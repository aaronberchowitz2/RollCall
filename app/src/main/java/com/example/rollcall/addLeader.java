package com.example.rollcall;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.net.URI;

public class addLeader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leader);
        Bundle b = getIntent().getExtras();
        String points = (String) b.get("points");



        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.name);

                Leaders leader = new Leaders(R.drawable.elon,name.getText().toString(),String.valueOf(points));

                Intent i = new Intent();
                i.putExtra("leader", leader);
                setResult(1,i);
                finish();



            }
        });
    }
}