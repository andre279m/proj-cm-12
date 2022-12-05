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

    public void addPlayer(String email) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        Query qname3 = databaseReference.orderByChild("email").equalTo(email);
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.v("datas", ""+child.getValue());
                    PlayerModel playerModel = child.getValue(PlayerModel.class);
                    _playerModel.setValue(playerModel);
                    Log.v("dto",""+playerModel);
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        //_playerModel.setValue(playerModel);
    }

}
