package com.harny.valentin.androidmorpion;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Button gameResetButton;
    TextView player1InfoTextView, player2InfoTextView, gameRoundsTextView;

    int rounds = 1;
    int countActions = 0;
    boolean player1Turn = true;
    Button[][] buttons = new Button[3][3];
    Player player1;
    Player player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1 = (Player) getIntent().getSerializableExtra("player1");
        player2 = (Player) getIntent().getSerializableExtra("player2");

        gameResetButton = findViewById(R.id.gameResetButton);
        player1InfoTextView = findViewById(R.id.player1InfoTextView);
        player2InfoTextView = findViewById(R.id.player2InfoTextView);
        gameRoundsTextView = findViewById(R.id.gameRoundsTextView);

        updateInfo();
        setTurnIndicator();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String idButton = "button_" + i + j;
                buttons[i][j] = findViewById(getResources().getIdentifier(idButton, "id", getPackageName()));
                final int I = i;
                final int J = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!buttons[I][J].getText().toString().isEmpty()) {
                            return;
                        }
                        countActions++;
                        if (player1Turn) {
                            buttons[I][J].setText("X");
                            buttons[I][J].setTextColor(Color.BLACK);
                            player1Turn = false;
                        } else {
                            buttons[I][J].setText("O");
                            buttons[I][J].setTextColor(Color.RED);
                            player1Turn = true;
                        }
                        checkWin();
                        setTurnIndicator();
                    }
                });
            }
        }

        gameResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void checkWin() {
        for(int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString() == buttons[i][1].getText().toString() && buttons[i][1].getText().toString() == buttons[i][2].getText().toString() && !buttons[i][0].getText().toString().isEmpty()) {
                findWinner(buttons[i][0].getText().toString());
                return;
            }
        }

        for(int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().toString() == buttons[1][i].getText().toString() && buttons[1][i].getText().toString() == buttons[2][i].getText().toString() && !buttons[0][i].getText().toString().isEmpty()) {
                findWinner(buttons[0][i].getText().toString());
                return;
            }
        }

        if (buttons[0][0].getText().toString() == buttons[1][1].getText().toString() && buttons[1][1].getText().toString() == buttons[2][2].getText().toString() && !buttons[0][0].getText().toString().isEmpty()) {
            findWinner(buttons[0][0].getText().toString());
            return;
        }

        if (buttons[0][2].getText().toString() == buttons[1][1].getText().toString() && buttons[1][1].getText().toString() == buttons[2][0].getText().toString() && !buttons[0][2].getText().toString().isEmpty()) {
            findWinner(buttons[0][2].getText().toString());
            return;
        }

        if (countActions == 9) {
            resetGame();
        }
    }

    private void findWinner(String value) {
        switch (value) {
            case "X":
                player1.win();
                gameWinner(player1);
                break;
            case "O":
                player2.win();
                gameWinner(player2);
                break;
        }
        if (rounds < 5) {
            rounds += 1;
            updateInfo();
            resetGame();
        }
    }

    private void gameWinner(Player player) {
        if (player.getScore() == 3) {
            player1.setScore(0);
            player2.setScore(0);
            Intent winActivity = new Intent(this, WinActivity.class);
            winActivity.putExtra("winnerName", player.getName());
            winActivity.putExtra("player1", player1);
            winActivity.putExtra("player2", player2);
            startActivity(winActivity);
        }
    }

    private void setTurnIndicator() {
        if (player1Turn) {
            player1InfoTextView.setBackgroundColor(Color.GREEN);
            player2InfoTextView.setBackgroundColor(Color.TRANSPARENT);
        } else {
            player2InfoTextView.setBackgroundColor(Color.GREEN);
            player1InfoTextView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void resetGame() {
        countActions = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++ ) {
                buttons[i][j].setText("");
            }
        }
    }

    private void updateInfo() {
        gameRoundsTextView.setText(("Rounds : " + rounds + "/5"));
        player1InfoTextView.setText(("(X) " + player1.getName() + " : " + player1.getScore()));
        player2InfoTextView.setText(("(O) " + player2.getName() + " : " + player2.getScore()));
    }

    @Override
    public void onBackPressed() {}
}
