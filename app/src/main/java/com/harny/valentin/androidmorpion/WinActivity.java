package com.harny.valentin.androidmorpion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    Button winRevengeButton, winReplayButton, winExitButton;
    TextView winWinnerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        winRevengeButton = findViewById(R.id.winRevengeButton);
        winReplayButton = findViewById(R.id.winReplayButton);
        winExitButton = findViewById(R.id.winExitButton);
        winWinnerTextView = findViewById(R.id.winWinnerTextView);

        winWinnerTextView.setText(getIntent().getExtras().getString("winnerName"));

        winRevengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivity = new Intent(winRevengeButton.getContext(), GameActivity.class);
                gameActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                gameActivity.putExtra("player1", getIntent().getSerializableExtra("player1"));
                gameActivity.putExtra("player2", getIntent().getSerializableExtra("player2"));
                startActivity(gameActivity);
            }
        });

        winReplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerActivity = new Intent(winReplayButton.getContext(), PlayerActivity.class);
                playerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                playerActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(playerActivity);
            }
        });

        winExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
