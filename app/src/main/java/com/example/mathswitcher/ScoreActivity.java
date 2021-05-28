package com.example.mathswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    TextView txtScoVal, txtTimeScore, txtStrkScore, txtTotlScore, txtMess;
    Button btnMenu;

    int score = 0;
    int total = 0;
    int maxStreak = 0;
    int timeElapsed = 0;
    int time = 61;

    int tScore = 0;
    int sScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.score = extras.getInt("s");
            this.timeElapsed = extras.getInt("t");
            this.maxStreak = extras.getInt("c");
        }


        txtScoVal = (TextView) findViewById(R.id.txtScoVal);
        txtTimeScore = (TextView) findViewById(R.id.txtTimeScore);
        txtStrkScore = (TextView) findViewById(R.id.txtStrkScore);
        txtTotlScore = (TextView) findViewById(R.id.txtTotlScore);
        txtMess = (TextView) findViewById(R.id.txtMess);

        btnMenu = (Button) findViewById(R.id.btnMenu);

        txtScoVal.setText(String.valueOf(score));
        calcTimeScore();
        calcStreakScore();
        calcTotal();

        setMess();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public void calcTimeScore() {
        int timed = (this.time - timeElapsed -1);
        int scored = timed * 5;
        this.tScore = scored;
        txtTimeScore.setText(String.valueOf(tScore));
    }

    public void calcStreakScore() {
        int scored = maxStreak * 20;
        this.sScore = scored;
        txtStrkScore.setText(String.valueOf(sScore));
    }

    public void calcTotal() {
        this.total = this.score + this.tScore + this.sScore;
        txtTotlScore.setText(String.valueOf(this.total));
    }

    public void setMess() {
        if (total >= 950) {
            txtMess.setText("Perfect! Unimaginable...");
        }else if (total >=650 && score < 950) {
            txtMess.setText("More practice to be a guru!");
        }else if (total >=450 && score < 650) {
            txtMess.setText("Good enough. Passable.");
        }else if (total < 450) {
            txtMess.setText("You need to work on more maths!");
        }
    }


}
