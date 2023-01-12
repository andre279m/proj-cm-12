package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class GameEnd extends AppCompatActivity {
    private SharedViewModel sharedViewModel;
    private AccelerometorSensor accelerometorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        Intent intent = getIntent();

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        String state = intent.getStringExtra("State");
        String score = intent.getStringExtra("Score");

        TextView gameState = findViewById(R.id.gameState);
        gameState.setText(state);
        TextView gameScore = findViewById(R.id.gameScore);
        gameScore.setText("Your score is: " + score + "!");

        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(__ -> restartGame());

        sharedViewModel.addScore(Integer.parseInt(score));
    }

    private void restartGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onResume() {
        super.onResume();
        accelerometorSensor.register();
    }

    @Override
    public void onPause() {
        super.onPause();
        accelerometorSensor.unRegister();
    }
}