package com.example.ingame;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnDoubleTapListener {

    private PuzzleCanvas canvas;

    void setCanvas(PuzzleCanvas canvas) {
        this.canvas = canvas;
    }

    ////////SimpleOnGestureListener
    /**@Override
    public void onLongPress(MotionEvent motionEvent) {
        canvas.changeBackground();
    }**/

}