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
        this.scoreQuiz = 0;
        this.scorePuzzle = 0;
        this.scoreSimon = 0;

        this.email = "Alex@email.com";
    };
    public PlayerModel(String disability, String name, int scoreSimon, int scorePuzzle, int scoreQuiz, String email) {
        this.disability = disability;
        this.name = name;
        this.scoreSimon = scoreSimon;
        this.scorePuzzle = scorePuzzle;
        this.scoreQuiz = scoreQuiz;
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

    public int getScoreSimon() {
        return scoreSimon;
    }

    public void setScoreSimon(int scoreSimon) {
        this.scoreSimon = scoreSimon;
    }

    public int getScorePuzzle() {
        return scorePuzzle;
    }

    public void setScorePuzzle(int scorePuzzle) {
        this.scorePuzzle = scorePuzzle;
    }

    public int getScoreQuiz() {
        return scoreQuiz;
    }

    public void setScoreQuiz(int scoreQuiz) {
        this.scoreQuiz = scoreQuiz;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}





}
