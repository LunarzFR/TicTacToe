package com.harny.valentin.androidmorpion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button startPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startPlayButton = findViewById(R.id.startPlayButton);

        startPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerActivity = new Intent(startPlayButton.getContext(), PlayerActivity.class);
                startActivity(playerActivity);
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
