package com.example.ingame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLeaderBoard extends AppCompatActivity {

    //initialize buttons
    private Button buttonSimon;
    private Button buttonPuzzle;
    private Button buttonTrivia;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        //obtain information from the player ID
        intent = getIntent();
        String playerID = intent.getStringExtra("playerID");

        // connect buttons to their corresponding View
        //buttonSimon = findViewById(R.id.button2);
        //buttonPuzzle = findViewById(R.id.button3);
        //buttonTrivia = findViewById(R.id.button4);

        // Click listeners for each button
        buttonSimon.setOnClickListener(__ -> onButtonChooseSimon(playerID));
        buttonPuzzle.setOnClickListener(__ -> onButtonChoosePuzzle(playerID));
        buttonTrivia.setOnClickListener( __ -> onButtonChooseTrivia(playerID));
    }

    private void onButtonChooseSimon(String playerID) {
        Intent intentSimonHighscoreScreen = new Intent(this, HighScoreActivity.class);
        intentSimonHighscoreScreen.putExtra("playerID", playerID);
        intentSimonHighscoreScreen.putExtra("class", "Simon");
        startActivity(intentSimonHighscoreScreen);
    }

    private void onButtonChoosePuzzle(String playerID) {
        Intent intentPuzzleHighscoreScreen = new Intent(this, HighScoreActivity.class);
        intentPuzzleHighscoreScreen.putExtra("playerID", playerID);
        intentPuzzleHighscoreScreen.putExtra("class", "Puzzle");
        startActivity(intentPuzzleHighscoreScreen);
    }

    private void onButtonChooseTrivia(String playerID) {
        Intent intent = new Intent(this, HighScoreActivity.class);
        intent.putExtra("playerID", playerID);
        intent.putExtra("class", "Trivia");
        startActivity(intent);
    }
}