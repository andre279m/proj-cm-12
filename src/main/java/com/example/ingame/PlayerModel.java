package com.example.ingame;

public class PlayerModel {
    String disability;
    int score;
    String email;
    String name;

    public PlayerModel() {
        this.disability = "None";
        this.name = "Alex";
        this.score = 0;
        this.email = "Alex@email.com";
    };
    public PlayerModel(String disability, String name, int score, String email) {
        this.disability = disability;
        this.name = name;
        this.score = score;
        this.email = email;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
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
