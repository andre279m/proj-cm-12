package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.GestureDetector;

public class Puzzle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(getApplicationContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);

        PuzzleCanvas puzzleCanvas = new PuzzleCanvas(getApplicationContext(), null, mGestureDetector, v);
        mGestureListener.setCanvas(puzzleCanvas);

        setContentView(puzzleCanvas);// adds the created view to the screen
    }
}