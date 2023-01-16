package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //initialize buttons
    private Button buttonHighScore;
    private Button buttonSetUp;
    private Button buttonLogout;
    private Button buttonPlay;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtain information from the player ID
        intent = getIntent();
        String playerID = intent.getStringExtra("playerID");

        // connect buttons to their corresponding View
        buttonHighScore = findViewById(R.id.highscore);
        buttonSetUp = findViewById(R.id.setup);
        buttonLogout = findViewById(R.id.logout);
        buttonPlay = findViewById(R.id.play);

        // Click listeners for each button
        buttonHighScore.setOnClickListener(__ -> onButtonHighScoreClicked());
        buttonSetUp.setOnClickListener(__ -> onButtonSetUpClicked(playerID));
        buttonLogout.setOnClickListener(__ -> onButtonLogoutClicked());
        buttonPlay.setOnClickListener( __ -> startGame(playerID));
    }


    // Methods to start new activities
    private void onButtonLogoutClicked() {
        FirebaseAuth.getInstance().signOut();
        Intent intentReturnLoginPage = new Intent(this, AuthenticationPage.class);
        startActivity(intentReturnLoginPage);
        finish();
    }

    private void onButtonSetUpClicked(String playerID) {
        Intent intentChangeSetUpScreen = new Intent(this, SetUpActivity.class);
        intentChangeSetUpScreen.putExtra("playerID", playerID);
        startActivity(intentChangeSetUpScreen);
    }

    private void onButtonHighScoreClicked() {
        Intent intentChangeHighscoreScreen = new Intent(this, HighScoreActivity.class);
        startActivity(intentChangeHighscoreScreen);
    }

    private void startGame(String playerID) {
        Intent intent = new Intent(this, Simon.class);
        intent.putExtra("playerID", playerID);
        startActivity(intent);
    }
}