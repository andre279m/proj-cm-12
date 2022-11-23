package com.example.ingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class HighScoreActivity extends AppCompatActivity {
    String[] nameList = {"Juliano", "Alex", "Marty", "Gloria", "Melman", "Maurice", "Mort","Kowalski", "Skipper", "Rico", "Private"};
    int scoreList[] ={11,3,5,5,6,8,2,9,9,5,4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent intent = getIntent();

        ListView listView = (ListView) findViewById(R.id.customListview);
        HighscoreAdapter highscoreAdapter = new HighscoreAdapter(getApplicationContext(),nameList,scoreList);
        listView.setAdapter(highscoreAdapter);
    }
}