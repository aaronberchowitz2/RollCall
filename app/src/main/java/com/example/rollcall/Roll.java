package com.example.rollcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.IOException;
import java.util.Random;

public class Roll extends AppCompatActivity {
    ImageView player, cpu;

    TextView p_score, cpu_score;
    int cpupoints = 0;
    private static int player_points;
    Random r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);


        cpu = (ImageView) findViewById(R.id.cpu);
        player = (ImageView) findViewById(R.id.player);

        cpu_score = (TextView) findViewById(R.id.cpu_score);
        p_score = (TextView) findViewById(R.id.player_score);

        r = new Random();

        Button rolling = (Button) findViewById(R.id.rollbtn);
        rolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cpupoints < 10) {


                    int cpuThrow = r.nextInt(6) + 1;
                    int playerThrow = r.nextInt(6) + 1;

                    setImageCPU(cpuThrow);
                    setImagePlayer(playerThrow);

                    if (cpuThrow > playerThrow) {
                        cpupoints++;
                    }

                    if (playerThrow > cpuThrow) {
                        player_points++;
                    }

                    cpu_score.setText("CPU: " + cpupoints);
                    p_score.setText("Player: " + player_points);
                }


               else {
                    if (cpupoints==10 && player_points >= 10) {
                        p_score.setText("Player won");
                        cpu_score.setText("CPU lost");
                    } else {
                        p_score.setText("Player lost");
                        cpu_score.setText("CPU won");
                    }

                }
            }


        });

        Button btn2 = findViewById(R.id.leadersbtn);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), LeaderboardActivity.class);
                i.putExtra("account", MainActivity.signedacc());


                startActivity(i);

            }
        });

    }
    public static String points (){
        return String.valueOf(player_points);
    }

    public void setImageCPU(int num){
        switch (num){
            case 1:
                cpu.setImageResource(R.drawable.one);
                break;
            case 2:
                cpu.setImageResource(R.drawable.two);
                break;
            case 3:
                cpu.setImageResource(R.drawable.three);
                break;
            case 4:
                cpu.setImageResource(R.drawable.four);
                break;
            case 5:
                cpu.setImageResource(R.drawable.five);
                break;
            case 6:
                cpu.setImageResource(R.drawable.six);
                break;

        }
    }



    public void setImagePlayer(int num) {
        switch (num) {
            case 1:
                player.setImageResource(R.drawable.one);
                break;
            case 2:
                player.setImageResource(R.drawable.two);
                break;
            case 3:
                player.setImageResource(R.drawable.three);
                break;
            case 4:
                player.setImageResource(R.drawable.four);
                break;
            case 5:
                player.setImageResource(R.drawable.five);
                break;
            case 6:
                player.setImageResource(R.drawable.six);
                break;

        }
    }
}