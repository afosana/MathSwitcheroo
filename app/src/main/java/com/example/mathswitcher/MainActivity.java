package com.example.mathswitcher;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer menuRing;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button play = findViewById(R.id.Play);
        final Button howToPlay = findViewById(R.id.HowToPlay);
        final Button exit = findViewById(R.id.Exit);

        menuRing = MediaPlayer.create(MainActivity.this, R.raw.bg);
        menuRing.setLooping(true);
        menuRing.start();

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadingScreen.class));
                menuRing.stop();
                finish();
            }
        });

        howToPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
                finish();
            }
        });


        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                menuRing.stop();
                finish();
                System.exit(0);
            }
        });
    }
}