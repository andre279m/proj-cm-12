package com.example.ingame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("ViewConstructor")
public class PuzzleCanvas extends View implements View.OnTouchListener{

    private final PuzzleFragment pf;
    private int mScore = 0;
    private int mPuzzleNumber = 0;

    private int currentRound = 0;
    private Timer timer = new Timer("puzzle");

    private final TextView mScoreView;

    private final String playerID;

    private final GestureDetector mGestureDetector;

    private final Vibrator v;

    private final String TAG = PuzzleCanvas.class.getSimpleName();

    private final float[][] goal = {{0.3f,0.3f},{}};//TODO adicionar mais posiçoes

    public PuzzleCanvas(Context context, @Nullable AttributeSet attrs, GestureDetector mGestureDetector, Vibrator v, TextView mScoreView, String playerID, PuzzleFragment pf) {
        super(context, attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        this.v = v;
        this.mScoreView = mScoreView;
        this.playerID = playerID;
        this.pf = pf;
        timer.schedule(new TTask(timer), 0, 1000);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float distance = calculateDistanceBetweenPointsWithHypot(eventX, eventY, goal[mPuzzleNumber][0]*getScreenWidth(), goal[mPuzzleNumber][1]*getScreenHeight());
                long[] ts = {10};
                int a = (int) (-(distance /5) + 255);//TODO ajustes a sensibilidade
                if (a < 0)
                    a=0;
                int[] as = {a};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createWaveform(ts,as, -1));
                }
                if (distance < 50) {
                    Toast.makeText(pf.getActivity(), "Found it!", Toast.LENGTH_SHORT).show();
                    mScore+=1;
                    updateScore();
                    try {
                        updatePuzzle();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                distance = calculateDistanceBetweenPointsWithHypot(eventX,eventY,goal[mPuzzleNumber][0],goal[mPuzzleNumber][1]);
                ts = new long[]{10};
                a = (int) (-(distance /5) + 255);//TODO ajustes a sensibilidade
                if (a < 0)
                    a=0;
                Log.d(TAG, distance + " ola " + a);
                as = new int[]{a};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createWaveform(ts,as, -1));
                }
                if (distance < 50){
                    Toast.makeText(pf.getActivity(), "Found it!", Toast.LENGTH_SHORT).show();
                    updateScore();
                    try {
                        updatePuzzle();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:// when you lift your finger
                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    public float calculateDistanceBetweenPointsWithHypot(
            float x1,
            float y1,
            float x2,
            float y2) {

        float ac = Math.abs(y2 - y1);
        float cb = Math.abs(x2 - x1);

        return (float) Math.hypot(ac, cb);
    }


    private void updatePuzzle() throws InterruptedException {
        Random r = new Random();
        mPuzzleNumber = r.nextInt(goal.length);
        timer.cancel();
        timer.purge();
        Thread.sleep(1000);
        timer = new Timer();
        timer.schedule(new TTask(timer), 0, 1000);
        currentRound++;
        int rounds = 5;
        if (currentRound >= rounds) {
            timer.cancel();
            timer.purge();
            Intent intentGameEnd = new Intent(getContext(), GameEnd.class);
            intentGameEnd.putExtra("State", "Game finished!");
            intentGameEnd.putExtra("Score", mScore);//TODO mudar para algo com tempo
            intentGameEnd.putExtra("playerID", playerID);
            pf.requireActivity().startActivity(intentGameEnd);
            pf.requireActivity().finish();
        }
    }


    @SuppressLint("SetTextI18n")
    private void updateScore() {
        mScoreView.setText("" + mScore);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
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

                pf.requireActivity().runOnUiThread(() -> Toast.makeText(pf.requireActivity(), "Timed out", Toast.LENGTH_SHORT).show());
                try {
                    updatePuzzle();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                System.out.println("Time left:" + (seconds - (i % seconds))); //TODO atualizar tempo no ecra?
        }
    }


}



