package com.harny.valentin.androidmorpion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    Button playerNextButton;
    EditText playerNameEditText;
    TextView playerNumberTextView;
    Player player1, player2;
    int playerNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerNextButton = findViewById(R.id.playerNextButton);
        playerNameEditText = findViewById(R.id.playerNameEditText);
        playerNumberTextView = findViewById(R.id.playerNumberTextView);

        playerNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PlayerActivity.this);
                builder.setMessage("Invalid name!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alert = builder.create();

                switch (playerNumber) {
                    case 1:
                        if (playerNameEditText.getText().length() < 2 || playerNameEditText.getText().length() > 12) {
                            alert.show();
                        } else {
                            player1 = new Player(playerNameEditText.getText().toString());
                            playerNameEditText.setText("");
                            playerNumberTextView.setText(("Player 2"));
                            playerNumber = 2;
                        }
                        break;
                    case 2:
                        if (playerNameEditText.getText().length() < 2 || playerNameEditText.getText().length() > 12) {
                            alert.show();
                        } else {
                            player2 = new Player(playerNameEditText.getText().toString());
                            Intent gameActivity = new Intent(playerNextButton.getContext(), GameActivity.class);
                            gameActivity.putExtra("player1", player1);
                            gameActivity.putExtra("player2", player2);
                            startActivity(gameActivity);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
