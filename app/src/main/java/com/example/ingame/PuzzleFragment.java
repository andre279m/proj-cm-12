package com.example.ingame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PuzzleFragment extends Fragment {

    private PuzzleCanvas puzzleCanvas;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(getActivity().getApplicationContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);

        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        PuzzleCanvas puzzleCanvas = new PuzzleCanvas(getActivity().getApplicationContext(), null, mGestureDetector,v);
        mGestureListener.setCanvas(puzzleCanvas);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_puzzle, container, false);
        ConstraintLayout cl = view.findViewById(R.id.canvas);
        cl.addView(puzzleCanvas);

        // Inflate the layout for this fragment
        return view;
    }
}