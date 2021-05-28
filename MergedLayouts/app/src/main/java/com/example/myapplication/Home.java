package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button play = findViewById(R.id.Play);
        final Button settings = findViewById(R.id.Settings);
        final Button howToPlay = findViewById(R.id.HowToPlay);
        final Button exit = findViewById(R.id.Exit);
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.INVISIBLE);
                howToPlay.setVisibility(View.INVISIBLE);
                exit.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                Handler handler = new Handler();                                                                        //To initiate loading screen
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Home.this, SwipeLeftRight.class));
                        finish();
                    }
                }, 4000);
            }
        });

        howToPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.INVISIBLE);
                howToPlay.setVisibility(View.INVISIBLE);
                exit.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                Handler handler = new Handler();                                                                        //To initiate loading screen
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Home.this, HowToPlay.class));
                        finish();
                    }
                }, 4000);
            }
        });


        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Settings.class));
            }
        });


        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}