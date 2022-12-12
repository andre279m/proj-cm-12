package com.example.ingame;

import java.util.ArrayList;

public class PlayerModel {
    int rank;
    int score;
    String email;
    String name;

    public PlayerModel() {
        this.rank = 1;
        this.name = "Alex";
        this.score = 0;
        this.email = "Alex@email.com";
    };
    public PlayerModel(int rank, String name, int score, String email) {
        this.rank = rank;
        this.name = name;
        this.score = score;
        this.email = email;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}





}
