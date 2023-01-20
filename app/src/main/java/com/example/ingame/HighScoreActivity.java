package com.example.ingame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class HighScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    PlayerAdapter playerAdapter;
    ArrayList<PlayerModel> list;
    private String classe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent intent = getIntent();
        recyclerView = findViewById(R.id.highScoreRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        list = new ArrayList<>();
        classe = intent.getStringExtra("class");
        playerAdapter = new PlayerAdapter(this,list, classe);
        recyclerView.setAdapter(playerAdapter);
        Log.v("why",""+list);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    PlayerModel player = dataSnapshot.getValue(PlayerModel.class);
                    list.add(player);

                }
                switch (classe) {
                    case "Simon":

                        Collections.sort(
                                list,
                                (player1, player2) -> player2.getScoreSimon() - player1.getScoreSimon());

                        Log.v("HighScore", "Simon Highscore sorted");

                        break;
                    case "Puzzle":

                        Collections.sort(
                                list,
                                (player1, player2) -> player2.getScorePuzzle() - player1.getScorePuzzle());

                        Log.v("HighScore", "Puzzle Highscore sorted");

                        break;
                    case "Trivia":

                        Collections.sort(
                                list,
                                (player1, player2) -> player2.getScoreQuiz() - player1.getScoreQuiz());

                        Log.v("HighScore", "Trivia Highscore sorted");
                        break;
                }

                playerAdapter = new PlayerAdapter(HighScoreActivity.this,list, classe);
                recyclerView.setAdapter(playerAdapter);
                playerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });


    }
}