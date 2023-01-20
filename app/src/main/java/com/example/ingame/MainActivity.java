package com.example.ingame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtain information from the player ID
        Intent intent = getIntent();
        String playerID = intent.getStringExtra("playerID");

        // connect buttons to their corresponding View
        //initialize buttons
        Button buttonHighScore = findViewById(R.id.highscore);
        Button buttonSetUp = findViewById(R.id.setup);
        Button buttonLogout = findViewById(R.id.logout);
        Button buttonPlay = findViewById(R.id.play);

        // Click listeners for each button
        buttonHighScore.setOnClickListener(__ -> onButtonHighScoreClicked());
        buttonSetUp.setOnClickListener(__ -> onButtonSetUpClicked(playerID));
        buttonLogout.setOnClickListener(__ -> onButtonLogoutClicked());
        buttonPlay.setOnClickListener(__ -> startGame(playerID));
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
        Intent intentChangeHighscoreScreen = new Intent(this, ChooseLeaderBoard.class);
        startActivity(intentChangeHighscoreScreen);
    }

    private void startGame(String playerID) {
        Intent intent = new Intent(this, ChooseGame.class);
        intent.putExtra("playerID", playerID);
        startActivity(intent);
    }
}