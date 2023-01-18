package com.example.ingame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Trivia extends AppCompatActivity {

    private final QuestionsLibrary mQuestionLibrary = new QuestionsLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    private int currentRound = 0;
    private Timer timer;

    private String playerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        Intent intent = getIntent();
        playerID = intent.getStringExtra("playerID");

        mScoreView = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);

        timer = new Timer("trivia");

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(view -> {
            //My logic for Button goes in here

            if (mButtonChoice1.getText() == mAnswer) {
                mScore += 1;
                updateScore();
                updateQuestion();
                //This line of code is optiona
                Toast.makeText(Trivia.this, "correct", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Trivia.this, "wrong", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(view -> {
            //My logic for Button goes in here

            if (mButtonChoice2.getText() == mAnswer) {
                mScore = mScore + 1;
                updateScore();
                updateQuestion();
                //This line of code is optiona
                Toast.makeText(Trivia.this, "correct", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Trivia.this, "wrong", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(view -> {
            //My logic for Button goes in here

            if (mButtonChoice3.getText() == mAnswer) {
                mScore = mScore + 1;
                updateScore();
                updateQuestion();
                //This line of code is optiona
                Toast.makeText(Trivia.this, "correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Trivia.this, "wrong", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
        });

        //End of Button Listener for Button3

        //Start of Button Listener for Button4
        mButtonChoice4.setOnClickListener(view -> {
            //My logic for Button goes in here

            if (mButtonChoice4.getText() == mAnswer) {
                mScore = mScore + 1;
                updateScore();
                updateQuestion();
                //This line of code is optiona
                Toast.makeText(Trivia.this, "correct", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Trivia.this, "wrong", Toast.LENGTH_SHORT).show();
                updateQuestion();
            }
        });
        //End of Button Listener for Button4
    }

    private void updateQuestion() {
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        Random r = new Random();
        mQuestionNumber = r.nextInt(51);

        currentRound++;

        timer.cancel();
        timer.purge();
        timer = new Timer();
        timer.schedule(new TTask(timer), 0, 1000);

        int rounds = 5;
        if (currentRound >= rounds) {
            timer.cancel();
            timer.purge();
            Intent intentGameEnd = new Intent(this, GameEnd.class);
            intentGameEnd.putExtra("State", "Game finished!");
            intentGameEnd.putExtra("Score", mScore);//TODO mudar para algo com tempo
            intentGameEnd.putExtra("playerID", playerID);
            startActivity(intentGameEnd);
            finish();
        }
    }


    @SuppressLint("SetTextI18n")
    private void updateScore() {
        mScoreView.setText("" + mScore);
    }

    class TTask extends TimerTask {

        final int seconds = 15;
        private int i = 0;

        Timer t;

        public TTask(Timer t){
            this.t = t;
        }

        @Override
        public void run() {
            i++;
            if (i % seconds == 0){
                t.cancel();
                t.purge();

                Trivia.this.runOnUiThread(()-> Toast.makeText(Trivia.this, "Timed out", Toast.LENGTH_SHORT).show());
                updateQuestion();
            }
            else
                System.out.println("Time left:" + (seconds - (i % seconds))); //TODO atualizar tempo no ecra?
        }
    }
}