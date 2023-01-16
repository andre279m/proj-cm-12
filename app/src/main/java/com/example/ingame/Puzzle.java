package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.GestureDetector;

public class Puzzle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);// adds the created view to the screen

        PuzzleFragment pf = new PuzzleFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.puzzleFrag, pf);

        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }
}