package com.example.ingame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PuzzleFragment extends Fragment {

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GestureListener mGestureListener = new GestureListener();
        GestureDetector mGestureDetector = new GestureDetector(requireActivity().getApplicationContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);

        Intent intent = requireActivity().getIntent();
        String playerID = intent.getStringExtra("playerID");

        View view = inflater.inflate(R.layout.fragment_puzzle, container, false);

        TextView mScoreView = view.findViewById(R.id.scorePuzzle);

        Vibrator v = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);

        PuzzleCanvas puzzleCanvas = new PuzzleCanvas(requireActivity().getApplicationContext(), null, mGestureDetector, v, mScoreView, playerID, this);
        mGestureListener.setCanvas(puzzleCanvas);

        // Inflate the layout for this fragment
        LinearLayout ll = view.findViewById(R.id.canvas);
        ll.addView(puzzleCanvas);

        // Inflate the layout for this fragment
        return view;
    }
}