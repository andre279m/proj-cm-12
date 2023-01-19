package com.example.ingame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameEnd extends AppCompatActivity {
    private SharedViewModel sharedViewModel;
    private AccelerometorSensor accelerometorSensor;

    private String playerID;

    private String classe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        Intent intent = getIntent();
        //sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        String state = intent.getStringExtra("State");
        int score = intent.getIntExtra("Score",0);
        playerID = intent.getStringExtra("playerID");
        classe = intent.getStringExtra("class");

        Log.v("ENDGAME" , state + score + playerID + "");
        TextView gameState = findViewById(R.id.gameState);
        gameState.setText(state);
        TextView gameScore = findViewById(R.id.gameScore);
        gameScore.setText("Your score is: " + score + "!");

        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(__ -> restartGame());
        addScore(playerID, score);
    }

    private void restartGame() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("playerID", playerID);
        startActivity(intent);
        finish();
    }

    public void addScore(String playerID, int score){
        Log.v("HighScore", "Highscore updated" + score);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(playerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.v("HighScore", "Highscore updated" + snapshot);

                PlayerModel playerModel = snapshot.getValue(PlayerModel.class);
                Log.v("HighScore", "Highscore updated" + playerModel);

                if (classe.equals("Trivia")){
                    if (playerModel.getScoreQuiz() < score){
                        databaseReference.child(playerID).child("scoreQuiz").setValue(score);
                        Log.v("HighScore", "Highscore updated");
                    }
                    else {
                        databaseReference.child(playerID).child("scoreQuiz").setValue(playerModel.getScoreQuiz());
                        Log.v("HighScore", "Highscore updated");
                    }
                } else if (classe.equals("Puzzle")) {
                    if (playerModel.getScorePuzzle() < score){
                        databaseReference.child(playerID).child("scorePuzzle").setValue(score);
                        Log.v("HighScore", "Highscore updated");
                    }
                    else {
                        databaseReference.child(playerID).child("scorePuzzle").setValue(playerModel.getScorePuzzle());
                        Log.v("HighScore", "Highscore updated");
                    }
                } else if (classe.equals("Simon")) {
                    if (playerModel.getScoreSimon() < score){
                        databaseReference.child(playerID).child("scoreSimon").setValue(score);
                        Log.v("HighScore", "Highscore updated");
                    }
                    else {
                        databaseReference.child(playerID).child("scoreSimon").setValue(playerModel.getScoreSimon());
                        Log.v("HighScore", "Highscore updated");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}