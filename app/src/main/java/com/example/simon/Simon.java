package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.ingame.R;

public class Simon extends AppCompatActivity {
    private AccelerometorSensor accelerometorSensor;
    float [] history = new float[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        accelerometorSensor = new AccelerometorSensor(this);


        SharedViewModel model = new ViewModelProvider(this).get(SharedViewModel.class);
        model.simonAsk(5);

        TextView question = findViewById(R.id.question);

        model.getState().observe(this, round -> {
            if (round == null || round.getNumber() < 0) return;
            if (round.getState().name() == "ANSWERING") {
                question.setText(round.getQuestion().getQuote());
            } else {
                question.setText("Get Ready...");
            }

            Log.d("SimonActivity", String.format("[%d] Observed state change to [%s]. Question: [%s]", round.getNumber(), round.getState().name(), round.getQuestion().getQuote()));
            if (round.getState().name() == "WIN" || round.getState().name() == "GAME_OVER"){
                Intent intent = new Intent(this, GameEnd.class);
                intent.putExtra("State", round.getState().name());
                intent.putExtra("Score",round.getNumber().toString());
                startActivity(intent);
                finish();
            }


        });


        accelerometorSensor = new AccelerometorSensor(this);
        accelerometorSensor.setListener((tx, ty, tz) -> {
            //Log.v("accel" , "right " + tx );
            float xChange = history[0] - tx;
            float yChange = history[1] - ty;
            history[0] = tx;
            history[1] = ty;

            if (xChange > 4){
                Log.v("accel" , "right " + xChange );
                model.answer(Movement.RIGHT);
                history = new float[2];

            }
            else if (xChange < -4){
                Log.v("accel" , "left " + xChange );
                model.answer(Movement.LEFT);}
            history = new float[2];


            if (yChange > 4){
                Log.v("accel" , "up " + yChange );
                model.answer(Movement.UP);
                history = new float[2];

            }
            else if (yChange < -2){
                Log.v("accel" , "down " + yChange );
                model.answer(Movement.DOWN);
                history = new float[2];

            }
            });


        Button blue = findViewById(R.id.blue);
        Button green = findViewById(R.id.green);
        Button red = findViewById(R.id.red);
        Button yellow = findViewById(R.id.yellow);

        blue.setOnClickListener(__ -> model.answer(Movement.RIGHT));
        green.setOnClickListener(__ -> model.answer(Movement.LEFT));
        red.setOnClickListener(__ -> model.answer(Movement.DOWN));
        yellow.setOnClickListener(__ -> model.answer(Movement.UP));
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