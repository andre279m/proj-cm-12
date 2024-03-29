package com.example.ingame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLeaderBoard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_leaderboard);

        //obtain information from the player ID
        Intent intent = getIntent();
        String playerID = intent.getStringExtra("playerID");

        // connect buttons to their corresponding View
        //initialize buttons
        Button buttonSimon = findViewById(R.id.simonBL);
        Button buttonPuzzle = findViewById(R.id.puzzleBL);
        Button buttonTrivia = findViewById(R.id.triviaBL);

        // Click listeners for each button
        buttonSimon.setOnClickListener(__ -> onButtonChooseSimon(playerID));
        buttonPuzzle.setOnClickListener(__ -> onButtonChoosePuzzle(playerID));
        buttonTrivia.setOnClickListener(__ -> onButtonChooseTrivia(playerID));
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