package com.example.ingame;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PuzzleCanvas extends View implements View.OnTouchListener{

    private GestureDetector mGestureDetector;

    private Vibrator v;

    private final String TAG = PuzzleCanvas.class.getSimpleName();

    private float distance;

    private float[] goal = {200.0f,300.0f};//TODO maybe mudar consoante o tamanho do ecra

    public PuzzleCanvas(Context context, @Nullable AttributeSet attrs, GestureDetector mGestureDetector, Vibrator v) {
        super(context, attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        this.v = v;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                distance = calculateDistanceBetweenPointsWithHypot(eventX,eventY,goal[0],goal[1]);
                long[] ts = {10};
                int a = (int) (-(distance/10) + 255);//TODO ajustes a sensibilidade
                int[] as = {a};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createWaveform(ts,as, -1));
                }
                if (distance < 100) {
                    Toast.makeText(this.getContext(), "correct", Toast.LENGTH_SHORT).show();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                distance = calculateDistanceBetweenPointsWithHypot(eventX,eventY,goal[0],goal[1]);
                ts = new long[]{10};
                a = (int) (-(distance/10) + 255);//TODO ajustes a sensibilidade
                Log.d(TAG, String.valueOf(distance) + " ola " + String.valueOf(a));
                as = new int[]{a};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createWaveform(ts,as, -1));
                }
                if (distance < 100){
                    Toast.makeText(this.getContext(), "correct", Toast.LENGTH_SHORT).show();
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
}
