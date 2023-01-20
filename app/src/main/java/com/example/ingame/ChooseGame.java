package com.example.ingame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        //obtain information from the player ID
        Intent intent = getIntent();
        String playerID = intent.getStringExtra("playerID");

        // connect buttons to their corresponding View
        //initialize buttons
        Button buttonSimon = findViewById(R.id.simonBG);
        Button buttonPuzzle = findViewById(R.id.puzzleBG);
        Button buttonTrivia = findViewById(R.id.triviaBG);

        // Click listeners for each button
        buttonSimon.setOnClickListener(__ -> onButtonChooseSimon(playerID));
        buttonPuzzle.setOnClickListener(__ -> onButtonChoosePuzzle(playerID));
        buttonTrivia.setOnClickListener(__ -> onButtonChooseTrivia(playerID));
    }

    private void onButtonChooseSimon(String playerID) {
        Intent intent = new Intent(this, Simon.class);
        intent.putExtra("playerID", playerID);
        startActivity(intent);
    }

    private void onButtonChoosePuzzle(String playerID) {
        Intent intent = new Intent(this, Puzzle.class);
        intent.putExtra("playerID", playerID);
        startActivity(intent);
    }

    private void onButtonChooseTrivia(String playerID) {
        Intent intent = new Intent(this, Trivia.class);
        intent.putExtra("playerID", playerID);
        startActivity(intent);
    }
}