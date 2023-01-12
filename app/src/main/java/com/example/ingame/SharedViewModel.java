package com.example.ingame;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<PlayerModel> _playerModel = new MutableLiveData<>(new PlayerModel());
    public LiveData<PlayerModel> getPlayer() {return _playerModel;}

    public void addPlayer(String userID) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlayerModel playerModel = snapshot.getValue(PlayerModel.class);
                Log.v("dto",""+playerModel);
                _playerModel.setValue(playerModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //_playerModel.setValue(playerModel);
    }

    public void addScore(int score){
        PlayerModel player = getPlayer().getValue();
        player.setScore(score);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.equalTo(player.email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot playerSnapshot: snapshot.getChildren()){
                    playerSnapshot.getRef().child("score").setValue(score);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}
