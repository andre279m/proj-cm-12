package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CustomListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_row_rview);
    }
}