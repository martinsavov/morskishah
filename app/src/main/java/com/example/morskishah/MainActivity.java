package com.example.morskishah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0:yellow , 1: red, 2: empty
    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPossitions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;


        Log.i("hello", counter.getTag().toString());
        //masiv koito da sledi igrata
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }
            counter.animate().translationYBy(1500).setDuration(1000);


            //syzdavam cikul koito obhojda vsichki pecheleshti pozicii i proverqva da ne sa ravni na 2: empty

            for (int[] winningPossition : winningPossitions) {
                if (gameState[winningPossition[0]] == gameState[winningPossition[1]] && gameState[winningPossition[1]] == gameState[winningPossition[2]] && gameState[winningPossition[0]] != 2) {

                    //ima pobeditel
                    gameActive = false;
                    String winner = "";

                    if (activePlayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }

                    Toast.makeText(this, winner + " has won", Toast.LENGTH_LONG).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
