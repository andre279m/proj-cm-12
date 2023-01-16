package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChooseGame extends AppCompatActivity {

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
        buttonSimon = findViewById(R.id.highscore);
        buttonPuzzle = findViewById(R.id.setup);
        buttonTrivia = findViewById(R.id.play);

        // Click listeners for each button
        buttonSimon.setOnClickListener(__ -> onButtonChooseSimon(playerID));
        buttonPuzzle.setOnClickListener(__ -> onButtonChoosePuzzle(playerID));
        buttonTrivia.setOnClickListener( __ -> onButtonChooseTrivia(playerID));
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