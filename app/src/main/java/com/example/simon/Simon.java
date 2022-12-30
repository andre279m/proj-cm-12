package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Simon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        SharedViewModel model = new ViewModelProvider(this).get(SharedViewModel.class);
        model.simonAsk(5);

        model.getState().observe(this, round -> {
            if (round == null || round.getNumber() < 0) return;
            Log.d("SimonActivity", String.format("[%d] Observed state change to [%s]. Question: [%s]", round.getNumber(), round.getState().name(), round.getQuestion().getQuote()));
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
}