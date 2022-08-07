package com.example.rollcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btn = findViewById(R.id.rollbtn);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Roll.class);
                startActivity(i);
            }
        });


        Button btn1 = findViewById(R.id.rulesbtn);
        btn1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Rules.class);
                startActivity(i);
            }
        });

        Button btn2 = findViewById(R.id.chatbtn);
        btn2.setOnClickListener(new View.OnClickListener() {

            GoogleSignInAccount acc = MainActivity.signedacc();
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ChatActivity.class);
                i.putExtra("user",acc);
                startActivity(i);

            }
        });
    }

}