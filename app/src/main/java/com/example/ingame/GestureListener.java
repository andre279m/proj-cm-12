package com.example.ingame;

import android.view.GestureDetector;

public class GestureListener extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnDoubleTapListener {

    private PuzzleCanvas canvas;

    void setCanvas(PuzzleCanvas canvas) {
        this.canvas = canvas;
    }

}