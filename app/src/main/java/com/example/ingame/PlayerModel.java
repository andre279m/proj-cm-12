package com.example.ingame;

public class PlayerModel {
    String disability;
    int scoreSimon;
    int scoreQuiz;
    int scorePuzzle;
    String email;
    String name;

    public PlayerModel() {
        this.disability = "None";
        this.name = "Alex";
        this.scoreSimon = 0;
        this.scorePuzzle = 0;
        this.scoreQuiz = 0;
        this.email = "Alex@email.com";
    }

    public String getDisability() {
        return disability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScoreSimon() {
        return scoreSimon;
    }

    public void setScoreSimon(int scoreSimon) {
        this.scoreSimon = scoreSimon;
    }

    public int getScorePuzzle() {
        return scorePuzzle;
    }

    public int getScoreQuiz() {
        return scoreQuiz;
    }

    public String getEmail() {return email;}


}
