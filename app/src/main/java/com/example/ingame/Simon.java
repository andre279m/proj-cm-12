package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Simon extends AppCompatActivity {

    private AccelerometorSensor accelerometorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        Intent intent = getIntent();
        String playerID = intent.getStringExtra("playerID");
        // start accelerometer sensor
        accelerometorSensor = new AccelerometorSensor(this);

        // Obtain and start game logic
        SimonSharedViewModel model = new ViewModelProvider(this).get(SimonSharedViewModel.class);
        model.simonAsk(5);

        TextView question = findViewById(R.id.question);

        model.getState().observe(this, round -> {
            if (round == null || round.getNumber() < 0) return;
            if (round.getState() == State.ANSWERING) {
                question.setText(round.getQuestion().getQuote());
            } else {
                question.setText("Get Ready...");
            }

            Log.d("SimonActivity", String.format("[%d] Observed state change to [%s]. Question: [%s]", round.getNumber(), round.getState().name(), round.getQuestion().getQuote()));
            if (round.getState() == State.WIN || round.getState() == State.GAME_OVER) {
                Log.d("SimonActivity", "Game of player " + playerID + " over due to " + round.getState());

                Intent intentGameEnd = new Intent(this, GameEnd.class);
                intentGameEnd.putExtra("State", round.getState().name());
                intentGameEnd.putExtra("Score", round.getNumber());
                intentGameEnd.putExtra("playerID", playerID);
                intentGameEnd.putExtra("class", "Simon");
                startActivity(intentGameEnd);
                finish();
            }


        });

        // definition of the movements and connecting each to their answer
        accelerometorSensor = new AccelerometorSensor(this);
        accelerometorSensor.setListener((tx, ty, tz) -> {
            //Log.v("accelerometer", "x " + tx + " y " + ty + " z " + tz);

            if (tx > 2) {
                Log.v("accelerometer", "right " + tx);
                model.answer(Movement.RIGHT);
            } else if (tx < -2) {
                Log.v("accelerometer", "left " + tx);
                model.answer(Movement.LEFT);
            }

            if (ty > 2) {
                Log.v("accelerometer", "up " + ty);
                model.answer(Movement.UP);
            } else if (tz > 2 && (tx > -2 && tx < 2) && ty > -2) {
                Log.v("accelerometer", "push " + tz);
                model.answer(Movement.PUSH);
            }
        });

        /*Button blue = findViewById(R.id.blue);
        Button green = findViewById(R.id.green);
        Button red = findViewById(R.id.red);
        Button yellow = findViewById(R.id.yellow);

        blue.setOnClickListener(__ -> model.answer(Movement.RIGHT));
        green.setOnClickListener(__ -> model.answer(Movement.LEFT));
        red.setOnClickListener(__ -> model.answer(Movement.PUSH));
        yellow.setOnClickListener(__ -> model.answer(Movement.UP));*/
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