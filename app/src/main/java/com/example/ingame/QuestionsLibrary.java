package com.example.ingame;

public class QuestionsLibrary {

    private String mQuestions [] = {
            "Which part of the plant holds it in the soil?",
            "This part of the plant absorbs energy from the sun.",
            "This part of the plant attracts bees, butterflies and hummingbirds.",
            "The _______ holds the plant upright."

    };

    private String mChoices [][] = {
            {"Roots", "Stem", "Flower"},
            {"Fruit", "Leaves", "Seeds"},
            {"Bark", "Flower", "Roots"},
            {"Flower", "Leaves", "Stem"}
    };

    private String mCorrectAnswers[] = {"Roots", "Leaves", "Flower", "Stem"};

    public String getQuestion(int a) {
        return mQuestions[a];
    }

    public String getChoice1(int a) {
        return mChoices[a][0];
    }

    public String getChoice2(int a) {
        return mChoices[a][1];
    }

    public String getChoice3(int a) {
        return mChoices[a][2];
    }

    public String getChoice4(int a) {
        return mChoices[a][3];
    }

    public String getCorrectAnswer(int a) {
        return mCorrectAnswers[a];
    }

}