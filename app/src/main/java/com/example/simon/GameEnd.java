package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class GameEnd extends AppCompatActivity {
    private AccelerometorSensor accelerometorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        Intent intent = getIntent();

        String state = intent.getStringExtra("State");
        String score = intent.getStringExtra("Score");

        TextView gameState = findViewById(R.id.gameState);
        gameState.setText(state);
        TextView gameScore = findViewById(R.id.gameScore);
        gameScore.setText("Your score is: " + score + "!");
        float [] history = new float[2];

        accelerometorSensor = new AccelerometorSensor(this);
        accelerometorSensor.setListener((tx, ty, tz) -> {
            //Log.v("accel" , "right " + tx );
            float xChange = history[0] - tx;
            float yChange = history[1] - ty;
            history[0] = tx;
            history[1] = ty;

            if (xChange > 2){
                Log.v("accel" , "right " + xChange );
            }
            else if (xChange < -2){
            Log.v("accel" , "left " + xChange );
            }
            /* if (tx > 6 && ty < 2 && ty > -2){
            Log.v("accel" , "right " + tx );}
            if (tx < -6 && ty < 2 && ty > -2){
                Log.v("accel" , "left " + tx );} */
        });


        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(__ -> restartGame());
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