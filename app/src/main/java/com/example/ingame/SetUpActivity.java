package com.example.ingame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SetUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Intent intent = getIntent();
        String playerID = intent.getStringExtra("playerID");
        Log.v("setup", playerID + "");

        TextView nameView = findViewById(R.id.name2);
        TextView disabilityView = findViewById(R.id.rank2);
        TextView scoreView = findViewById(R.id.score2);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(playerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlayerModel playerModel = snapshot.getValue(PlayerModel.class);
                Log.v("HighScore", "Highscore updated" + playerModel);
                assert playerModel != null;
                int sumScore = playerModel.getScoreSimon() + playerModel.getScorePuzzle() + playerModel.getScoreQuiz();
                nameView.setText(playerModel.getName());
                disabilityView.setText(playerModel.getDisability());
                scoreView.setText(""+ sumScore);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Button noneButton = findViewById(R.id.none);
        Button deafButton = findViewById(R.id.deaf);
        Button blindButton = findViewById(R.id.blind);

        noneButton.setOnClickListener(__ -> setDisabilityNone(playerID, "None"));
        deafButton.setOnClickListener(__ -> setDisabilityNone(playerID, "Deaf"));
        blindButton.setOnClickListener(__ -> setDisabilityNone(playerID, "Blind"));



    }

    private void setDisabilityNone(String playerID, String disability) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(playerID).child("disability").setValue(disability);
        TextView disabilityView = findViewById(R.id.rank2);
        disabilityView.setText(disability);
    }
}

