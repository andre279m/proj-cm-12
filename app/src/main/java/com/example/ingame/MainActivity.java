package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonHighScore = findViewById(R.id.highscore);
        Button buttonSetUp = findViewById(R.id.setup);


        buttonHighScore.setOnClickListener(__ -> onButtonHighScoreClicked());
        buttonSetUp.setOnClickListener(__ -> onButtonSetUpClicked());


    }

    private void onButtonSetUpClicked() {
        Intent intentChangeSetUpScreen = new Intent(this, SetUpActivity.class);
        startActivity(intentChangeSetUpScreen);
    }

    private void onButtonHighScoreClicked() {
        Intent intentChangeSetUpScreen = new Intent(this, HighScoreActivity.class);
        startActivity(intentChangeSetUpScreen);
    }
}