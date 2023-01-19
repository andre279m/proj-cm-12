package com.example.ingame;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    Context context;
    ArrayList<PlayerModel> list;
    String classe;

    public PlayerAdapter(Context context, ArrayList<PlayerModel> list, String classe) {
        this.context = context;
        this.list = list;
        this.classe = classe;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.highscore_row_rview,parent,false);
        return new PlayerAdapter.PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        PlayerModel player = list.get(position);
        holder.name.setText(player.getName());
        if (classe.equals("Simon")){

            holder.score.setText(Integer.toString(player.getScoreSimon()));

        } else if (classe.equals("Puzzle")) {

            holder.score.setText(Integer.toString(player.getScorePuzzle()));

        } else if (classe.equals("Trivia")) {

            holder.score.setText(Integer.toString(player.getScoreQuiz()));
        }
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView name,score;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.score);

        }
    }


}
