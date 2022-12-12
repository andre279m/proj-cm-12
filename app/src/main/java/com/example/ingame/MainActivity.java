package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    
    private Button buttonHighScore;
    private Button buttonSetUp;
    private Button buttonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHighScore = findViewById(R.id.highscore);
        buttonSetUp = findViewById(R.id.setup);
        buttonLogout = findViewById(R.id.logout);
        

        buttonHighScore.setOnClickListener(__ -> onButtonHighScoreClicked());
        buttonSetUp.setOnClickListener(__ -> onButtonSetUpClicked());
        buttonLogout.setOnClickListener(__ -> onButtonLogoutClicked());


    }

    private void onButtonLogoutClicked() {
        FirebaseAuth.getInstance().signOut();
        Intent intentReturnLoginPage = new Intent(this, AuthenticationPage.class);
        startActivity(intentReturnLoginPage);
        finish();


    }

    private void onButtonSetUpClicked() {
        Intent intentChangeSetUpScreen = new Intent(this, SetUpActivity.class);
        startActivity(intentChangeSetUpScreen);
    }

    private void onButtonHighScoreClicked() {
        Intent intentChangeHighscoreScreen = new Intent(this, HighScoreActivity.class);
        startActivity(intentChangeHighscoreScreen);
    }
}